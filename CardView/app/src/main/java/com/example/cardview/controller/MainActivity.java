package com.example.cardview.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cardview.R;
import com.example.cardview.adapter.Adapter;
import com.example.cardview.model.Postagens;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Postagens> lstPostagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        criarPostagens();

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //definir adapter
        Adapter adapter = new Adapter(lstPostagens);
        recyclerView.setAdapter(adapter);

    }

    public void criarPostagens() {
        Postagens postagens = new Postagens("Jean Gabriel Ferreira", "#TBT viagem legal", R.drawable.imagem1);
        lstPostagens.add(postagens);

        postagens = new Postagens("João Pedro Silva", "Viaje!", R.drawable.imagem2);
        lstPostagens.add(postagens);

        postagens = new Postagens("Maria Luiza", "#paris", R.drawable.imagem3);
        lstPostagens.add(postagens);

        postagens = new Postagens("Marcos Souza", "Que foto linda!", R.drawable.imagem4);
        lstPostagens.add(postagens);
    }


}
