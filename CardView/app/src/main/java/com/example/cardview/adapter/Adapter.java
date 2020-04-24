package com.example.cardview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;
import com.example.cardview.model.Postagens;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Postagens> lstPostagens = new ArrayList<>();

    public Adapter(List<Postagens> lstPostagens) {
        this.lstPostagens = lstPostagens;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemPostagens = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_postagens, parent, false);
        return new MyViewHolder(itemPostagens);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagens postagens = lstPostagens.get(position);
        holder.tvUsuario.setText(postagens.getUsuario());
        holder.tvPostagem.setText(postagens.getPostagem());
        holder.imgPostagem.setImageResource(postagens.getImagem());
    }

    @Override
    public int getItemCount() {
        return lstPostagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsuario, tvPostagem;
        private ImageView imgPostagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvPostagem = itemView.findViewById(R.id.tvPostagem);
            imgPostagem = itemView.findViewById(R.id.imgPostagem);
        }
    }

}
