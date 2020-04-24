package com.example.cardview.model;

public class Postagens {
    private String usuario;
    private String postagem;
    private int imagem;

    public Postagens(String usuario, String postagem, int imagem) {
        this.usuario = usuario;
        this.postagem = postagem;
        this.imagem = imagem;
    }

    public Postagens() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPostagem() {
        return postagem;
    }

    public void setPostagem(String postagem) {
        this.postagem = postagem;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
