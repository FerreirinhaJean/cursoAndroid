package com.example.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {

    private ImageView imgResultado;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        imgResultado = findViewById(R.id.imgResultado);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(eventoBtnVoltar);

        Bundle dados = getIntent().getExtras();
        int numero = dados.getInt("numero");

        if (numero == 0) {
            imgResultado.setImageResource(R.drawable.moeda_cara);
        } else {
            imgResultado.setImageResource(R.drawable.moeda_coroa);
        }
    }


    View.OnClickListener eventoBtnVoltar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


}
