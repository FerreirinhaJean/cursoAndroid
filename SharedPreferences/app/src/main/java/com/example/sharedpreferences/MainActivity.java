package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSalvar;
    private TextView tvUsuario;
    private TextInputEditText etNomeUsuario;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        tvUsuario = findViewById(R.id.tvUsuario);
        etNomeUsuario = findViewById(R.id.etNomeUsuario);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                //validar nome
                if (etNomeUsuario.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Preencha o nome", Toast.LENGTH_LONG).show();
                } else {
                    String nomeUsuario = etNomeUsuario.getText().toString();
                    editor.putString("nome", nomeUsuario);
                    editor.commit();
                    tvUsuario.setText("Olá, " + nomeUsuario);
                }
            }
        });

        //recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, MODE_PRIVATE);
        if (preferences.contains("nome")) {
            String nome = preferences.getString("nome", " usuário não definido");
            tvUsuario.setText("Olá, " + nome);
        } else {
            tvUsuario.setText("Olá, usuário não definido");
        }


    }
}
