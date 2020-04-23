package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listLocais;

    private String[] locais = {
            "Angra dos Reis",
            "Caldas Novas",
            "Campos do Jordão",
            "Porto Seguro",
            "Costa do Sauípe",
            "Ilhéus",
            "Tiradentes",
            "Praga",
            "Santiago",
            "Zurique",
            "Caribe",
            "Buenos Aires",
            "Budapeste",
            "Cancún",
            "Aruba"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listLocais = findViewById(R.id.listLocais);

        //criar adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, locais);

        listLocais.setAdapter(adapter);

        listLocais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valorSelecionado = listLocais.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, valorSelecionado, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
