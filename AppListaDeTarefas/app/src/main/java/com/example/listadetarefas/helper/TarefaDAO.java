package com.example.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements InterfaceTarefaDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", tarefa.getNomeTarefa());
            write.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Tarefa inserida!");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        try {
            ContentValues cv = new ContentValues();
            cv.put("nome", tarefa.getNomeTarefa());
            String[] args = {tarefa.getId().toString()};
            write.update(DbHelper.TABELA_TAREFAS, cv, "id = ?", args);
            Log.i("INFO", "Tarefa Atualizada!");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            write.delete(DbHelper.TABELA_TAREFAS, "id = ?", new String[]{tarefa.getId().toString()});
            Log.i("INFO", "Tarefa removida!");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao remover tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> listaTarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nome);

            listaTarefas.add(tarefa);
        }

        return listaTarefas;
    }
}
