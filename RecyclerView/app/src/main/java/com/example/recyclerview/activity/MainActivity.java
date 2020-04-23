package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.RecyclerItemClickListener;
import com.example.recyclerview.adapter.Adapter;
import com.example.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> lstFilmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //listagem de filmes
        criarFilmes();

        //configurar adapter
        Adapter adapter = new Adapter(lstFilmes);


        //configurar recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


        //evento de click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Filme filme = lstFilmes.get(position);
                Toast.makeText(MainActivity.this, "Item pressionado " + filme.getTitulo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Filme filme = lstFilmes.get(position);
                Toast.makeText(MainActivity.this, "Item longo " + filme.getGenero(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }


    public void criarFilmes() {
        Filme filme = new Filme("Homem Aranha", "2020", "Ação");
        lstFilmes.add(filme);

        filme = new Filme("Vingadores Ultimato", "2019", "Ação");
        lstFilmes.add(filme);

        filme = new Filme("It a Coisa", "2018", "Terror");
        lstFilmes.add(filme);

        filme = new Filme("Gente Grande", "2015", "Comédia");
        lstFilmes.add(filme);

        filme = new Filme("RED - Aposentados e perigosos", "2013", "Ação");
        lstFilmes.add(filme);

        filme = new Filme("Batman", "2020", "Ação");
        lstFilmes.add(filme);

        filme = new Filme("K-9", "2020", "Aventura");
        lstFilmes.add(filme);

        filme = new Filme("Coringa", "2020", "Ação/Suspense");
        lstFilmes.add(filme);

        filme = new Filme("A casa monstro", "2020", "Desenho");
        lstFilmes.add(filme);

        filme = new Filme("Desencanto", "2020", "Cartoon");
        lstFilmes.add(filme);

        filme = new Filme("O Poço", "2020", "Suspense");
        lstFilmes.add(filme);

        filme = new Filme("Vikings", "2020", "Ação");
        lstFilmes.add(filme);
    }
}
