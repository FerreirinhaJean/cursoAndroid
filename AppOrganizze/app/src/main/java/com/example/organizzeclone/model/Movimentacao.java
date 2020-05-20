package com.example.organizzeclone.model;

import com.example.organizzeclone.config.ConfiguracaoFireBase;
import com.example.organizzeclone.helper.Base64Custom;
import com.example.organizzeclone.helper.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;

    public Movimentacao() {

    }

    public Movimentacao(String data, String categoria, String descricao, String tipo, double valor) {
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void salvar(String data) {

        FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference reference = ConfiguracaoFireBase.getFireBase();
        reference.child("movimentacao").child(idUsuario).child(DateUtil.mesAnoDataEscolhida(data)).push().setValue(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}