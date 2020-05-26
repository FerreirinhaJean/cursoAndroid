package com.example.organizzeclone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzeclone.adapter.adapterMovimentacao;

import com.example.organizzeclone.R;
import com.example.organizzeclone.adapter.adapterMovimentacao;
import com.example.organizzeclone.config.ConfiguracaoFireBase;
import com.example.organizzeclone.helper.Base64Custom;
import com.example.organizzeclone.model.Movimentacao;
import com.example.organizzeclone.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private TextView tvSaldo, tvSaudacao;
    private RecyclerView recyclerMovimentos;
    private adapterMovimentacao adapterMovimentacao;
    private MaterialCalendarView calendarView;
    private DatabaseReference reference = ConfiguracaoFireBase.getFireBase();
    private FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
    private Double despesaTotal = 0.0;
    private Double receitaTotal = 0.0;
    private Double resumoUsuario = 0.0;

    private List<Movimentacao> lstMovimentacoes = new ArrayList<>();

    private DatabaseReference referenceUsuario;
    private ValueEventListener valueEventListener;
    private ValueEventListener valueEventListenerMovimentacoes;

    private DatabaseReference movimentacaoRef;
    private String mesAnoSelecionado;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Organizze");
        setSupportActionBar(toolbar);


        calendarView = findViewById(R.id.calendarView);
        tvSaldo = findViewById(R.id.tvSaldo);
        tvSaudacao = findViewById(R.id.tvSaudacao);
        recyclerMovimentos = findViewById(R.id.recyclerMovimentos);
        configuraCalendarView();

        //configura adapter
        adapterMovimentacao = new adapterMovimentacao(lstMovimentacoes, this);

        //configura recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMovimentos.setLayoutManager(layoutManager);
        recyclerMovimentos.setHasFixedSize(true);
        recyclerMovimentos.setAdapter(adapterMovimentacao);



    }

    public void recuperarMovimentacoes() {
        String idUsuario = autenticacao.getCurrentUser().getEmail();

        movimentacaoRef = reference.child("movimentacao").child(Base64Custom.codificarBase64(idUsuario))
                .child(mesAnoSelecionado);

        Log.i("MESANO",mesAnoSelecionado);

        valueEventListenerMovimentacoes = movimentacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lstMovimentacoes.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                    Log.i("Movimentacao", movimentacao.getDescricao());
                    lstMovimentacoes.add(movimentacao);

                }

                adapterMovimentacao.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        recuperarResumo();
        recuperarMovimentacoes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair:
                autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
                autenticacao.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void recuperarResumo() {

        String idUsuario = autenticacao.getCurrentUser().getEmail();
        referenceUsuario = reference.child("usuarios").child(Base64Custom.codificarBase64(idUsuario));

        valueEventListener = referenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Usuario usu = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usu.getDespesaTotal();
                receitaTotal = usu.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                DecimalFormat df = new DecimalFormat("0.##");

                tvSaldo.setText("R$ " + df.format(resumoUsuario));
                tvSaudacao.setText("Olá, " + usu.getNome());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        referenceUsuario.removeEventListener(valueEventListener);
        movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
    }

    public void adicionarDespesa(View view) {
        startActivity(new Intent(PrincipalActivity.this, DespesasActivity.class));
    }

    public void adicionarReceita(View view) {
        startActivity(new Intent(PrincipalActivity.this, ReceitasActivity.class));
    }

    public void configuraCalendarView() {
        CharSequence meses[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
                "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);

        CalendarDay dataAtual = calendarView.getCurrentDate();
        String mesSelecionado = String.format("%02d", (dataAtual.getMonth()));
        mesAnoSelecionado = String.valueOf(mesSelecionado + "" + dataAtual.getYear());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mesSelecionado = String.format("%02d", (date.getMonth()));
                mesAnoSelecionado = String.valueOf(mesSelecionado + "" + date.getYear());
                movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
                recuperarMovimentacoes();
            }
        });
    }

}
