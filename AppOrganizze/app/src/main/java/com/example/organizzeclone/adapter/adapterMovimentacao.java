package com.example.organizzeclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizzeclone.R;
import com.example.organizzeclone.model.Movimentacao;

import java.util.ArrayList;
import java.util.List;

public class adapterMovimentacao extends RecyclerView.Adapter<adapterMovimentacao.MyViewHolder> {

    List<Movimentacao> lstMovimetacoes;
    Context context;

    public adapterMovimentacao(List<Movimentacao> lstMovimetacoes, Context context) {
        this.lstMovimetacoes = lstMovimetacoes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movimentacao movimentacao = lstMovimetacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());
        holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccentReceita));

        if (movimentacao.getTipo().equals("d")) {
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("-" + movimentacao.getValor());
        }

    }

    @Override
    public int getItemCount() {
        return lstMovimetacoes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, valor, categoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.tvAdapterTitulo);
            valor = itemView.findViewById(R.id.tvAdapterValor);
            categoria = itemView.findViewById(R.id.tvAdapterCategoria);


        }
    }
}
