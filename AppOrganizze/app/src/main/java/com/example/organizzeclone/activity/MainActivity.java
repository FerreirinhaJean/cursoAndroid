package com.example.organizzeclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.organizzeclone.R;
import com.example.organizzeclone.config.ConfiguracaoFireBase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private Button btnCadastrar;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);


        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_3)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_4)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .build()
        );

    }


    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btnEntrar(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void btnCadastrar(View v) {
        startActivity(new Intent(MainActivity.this, CadastroActivity.class));
    }

    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal() {
        startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
        finish();
    }

}
