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

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText etDataDespesa, etCategoriaDespesa, etDescricaoDespesa;
    private EditText etValorDespesa;
    private Movimentacao movimentacao;
    private DatabaseReference reference = ConfiguracaoFireBase.getFireBase();
    private FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
    private Double despesaTotal;
    private Double despesaGerada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        etCategoriaDespesa = findViewById(R.id.etCategoriaDespesa);
        etDataDespesa = findViewById(R.id.etDataDespesa);
        etDescricaoDespesa = findViewById(R.id.etDescricaoDespesa);
        etValorDespesa = findViewById(R.id.etValorReceita);

        //preenche o campo dara com a date atual
        etDataDespesa.setText(DateUtil.dataAtual());

        recuperarDespesaTotal();

    }


    public void salvarDespesa(View view) {
        if (validarCamposDespesa()) {
            double valor = Double.parseDouble(etValorDespesa.getText().toString());
            String data = etDataDespesa.getText().toString();
            String categoria = etCategoriaDespesa.getText().toString();
            String descricao = etDescricaoDespesa.getText().toString();
            movimentacao = new Movimentacao(data, categoria, descricao, "d", valor);

            despesaGerada = valor;
            Double despesaAtualizada = despesaTotal + despesaGerada;
            atualizarDespesa(despesaAtualizada);

            movimentacao.salvar(data);
            finish();
        }
    }


    public Boolean validarCamposDespesa() {
        String valor = etValorDespesa.getText().toString();
        String data = etDataDespesa.getText().toString();
        String categoria = etCategoriaDespesa.getText().toString();
        String descricao = etDescricaoDespesa.getText().toString();
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

    public void recuperarDespesaTotal() {

        String idUsuario = autenticacao.getCurrentUser().getEmail();
        DatabaseReference usuario = reference.child("usuarios").child(Base64Custom.codificarBase64(idUsuario));

        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usu = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usu.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarDespesa(Double despesa) {
        String idUsuario = autenticacao.getCurrentUser().getEmail();
        DatabaseReference usuario = reference.child("usuarios").child(Base64Custom.codificarBase64(idUsuario));
        usuario.child("despesaTotal").setValue(despesa);

    }

}
