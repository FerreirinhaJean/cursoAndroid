package com.example.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSnackBar = findViewById(R.id.btnSnackBar);

        btnSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //necessario adicionar dependencia no build.gradle para o SnackBar
                Snackbar.make(v, "Botão Pressionado", Snackbar.LENGTH_LONG).setAction("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnSnackBar.setText("Botão alterado!");
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();

            }
        });


    }
}
