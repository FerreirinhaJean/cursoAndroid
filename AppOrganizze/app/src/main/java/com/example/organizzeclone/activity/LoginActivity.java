package com.example.organizzeclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizzeclone.R;
import com.example.organizzeclone.config.ConfiguracaoFireBase;
import com.example.organizzeclone.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmailLogin, etSenhaLogin;
    private Button btnEntrar;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etSenhaLogin = findViewById(R.id.etSenhaLogin);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailLogin.getText().toString();
                String senha = etSenhaLogin.getText().toString();

                if (!email.isEmpty()) {
                    if (!senha.isEmpty()) {
                        Usuario usuario = new Usuario(email, senha);
                        validaLogin(usuario);

                    } else {
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Preecha o email!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void validaLogin(Usuario usuario) {
        autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()

        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaPrincipal();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email/Senha não correspondem a um usuario cadastrado";
                    } catch (Exception e) {
                        excecao = "Erro ao fazer login: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
        finish();
    }


}
