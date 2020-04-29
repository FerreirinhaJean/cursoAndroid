package com.example.bancodadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

            //atualiza dado
            //bancoDados.execSQL("UPDATE pessoas set idade = 100 WHERE nome = 'Jean'");

            //deleta dado
            //bancoDados.execSQL("DELETE FROM pessoas WHERE id = 2");

            //deleta tabela
            // bancoDados.execSQL("DROP TABLE pessoas");

            //Inserir dados
            // bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Joao', 15)");
            // bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Maria', 55)");

            //recuperar dados
            //String consulta = "SELECT nome, idade FROM pessoas WHERE nome = 'Maria'";
            //String consulta = "SELECT nome, idade FROM pessoas WHERE idade > 18";
            //String consulta = "SELECT nome, idade FROM pessoas WHERE nome = 'Jean' AND idade = 20";
            String consulta = "SELECT id, nome, idade FROM pessoas";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //indices tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();//primeiro indice

            while (cursor != null) {

                int id = Integer.parseInt(cursor.getString(indiceId));
                String nome = cursor.getString(indiceNome);
                int idade = Integer.parseInt(cursor.getString(indiceIdade));

                Log.i("RESULTADO - Nome", nome + " idade: " + idade + " Id: " + id);
                cursor.moveToNext();//proximo indice
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
