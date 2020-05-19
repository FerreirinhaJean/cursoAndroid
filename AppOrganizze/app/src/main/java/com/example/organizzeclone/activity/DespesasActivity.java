package com.example.organizzeclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.organizzeclone.R;
import com.example.organizzeclone.helper.DateUtil;
import com.example.organizzeclone.model.Movimentacao;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.material.textfield.TextInputEditText;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText etDataDespesa, etCategoriaDespesa, etDescricaoDespesa;
    private EditText etValorDespesa;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        etCategoriaDespesa = findViewById(R.id.etCategoriaDespesa);
        etDataDespesa = findViewById(R.id.etDataDespesa);
        etDescricaoDespesa = findViewById(R.id.etDescricaoDespesa);
        etValorDespesa = findViewById(R.id.etValorDespesa);

        //preenche o campo dara com a date atual
        etDataDespesa.setText(DateUtil.dataAtual());
    }


    public void salvarDespesa(View view) {
        double valor = Double.parseDouble(etValorDespesa.getText().toString());
        String data = etDataDespesa.getText().toString();
        String categoria = etCategoriaDespesa.getText().toString();
        String descricao = etDescricaoDespesa.getText().toString();
        movimentacao = new Movimentacao(data, categoria, descricao, "d", valor);
        movimentacao.salvar(data);
        finish();
    }
}
