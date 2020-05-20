package com.example.organizzeclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizzeclone.R;
import com.example.organizzeclone.config.ConfiguracaoFireBase;
import com.example.organizzeclone.helper.Base64Custom;
import com.example.organizzeclone.helper.DateUtil;
import com.example.organizzeclone.model.Movimentacao;
import com.example.organizzeclone.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText etDataReceita, etCategoriaReceita, etDescricaoReceita;
    private EditText etValorReceita;
    private Movimentacao movimentacao;
    private DatabaseReference reference = ConfiguracaoFireBase.getFireBase();
    private FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
    private Double receitaTotal;
    private Double receitaGerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        etCategoriaReceita = findViewById(R.id.etCategoriaReceita);
        etDataReceita = findViewById(R.id.etDataReceita);
        etDescricaoReceita = findViewById(R.id.etDescricaoReceita);
        etValorReceita = findViewById(R.id.etValorReceita);

        etDataReceita.setText(DateUtil.dataAtual());

        recuperarReceitaTotal();
    }

    public void recuperarReceitaTotal() {

        String idUsuario = autenticacao.getCurrentUser().getEmail();
        DatabaseReference usuario = reference.child("usuarios").child(Base64Custom.codificarBase64(idUsuario));

        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usu = dataSnapshot.getValue(Usuario.class);
                receitaTotal = usu.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarReceita(Double receita) {
        String idUsuario = autenticacao.getCurrentUser().getEmail();
        DatabaseReference usuario = reference.child("usuarios").child(Base64Custom.codificarBase64(idUsuario));
        usuario.child("receitaTotal").setValue(receita);

    }


    public void salvarReceita(View view) {
        if (validarCamposReceita()) {
            double valor = Double.parseDouble(etValorReceita.getText().toString());
            String data = etDataReceita.getText().toString();
            String categoria = etCategoriaReceita.getText().toString();
            String descricao = etDescricaoReceita.getText().toString();
            movimentacao = new Movimentacao(data, categoria, descricao, "r", valor);

            receitaGerada = valor;
            Double receitaAtualizada = receitaTotal + receitaGerada;
            atualizarReceita(receitaAtualizada);

            movimentacao.salvar(data);
            finish();
        }
    }


    public Boolean validarCamposReceita() {
        String valor = etValorReceita.getText().toString();
        String data = etDataReceita.getText().toString();
        String categoria = etCategoriaReceita.getText().toString();
        String descricao = etDescricaoReceita.getText().toString();
        if (!valor.isEmpty()) {
            if (!data.isEmpty()) {
                if (!categoria.isEmpty()) {
                    if (!descricao.isEmpty()) {
                        return true;
                    } else {
                        Toast.makeText(this, "Descricao não preenchida", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(this, "Categoria não preenchida", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "Data não preenchida", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Valor não preenchida", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
