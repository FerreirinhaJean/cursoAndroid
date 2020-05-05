package com.example.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText etTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        etTarefa = findViewById(R.id.etTarefa);

        //recuperar tarefa ,caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto
        if (tarefaAtual != null) {
            etTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuAdicionar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                if (tarefaAtual != null) {//edicao
                    if (!etTarefa.getText().toString().equals("")) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(etTarefa.getText().toString());
                        tarefa.setId(tarefaAtual.getId());
                        if(tarefaDAO.atualizar(tarefa)){
                            Toast.makeText(this, "Tarefa Atualizada!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(this, "Erro ao atualizar Tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {//salvar
                    Tarefa tarefa = new Tarefa();
                    if (!etTarefa.getText().toString().equals("")) {
                        tarefa.setNomeTarefa(etTarefa.getText().toString());
                        if (tarefaDAO.salvar(tarefa)) {
                            Toast.makeText(this, "Tarefa Salva!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, "Erro ao salvar Tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Informe a tarefa!", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
