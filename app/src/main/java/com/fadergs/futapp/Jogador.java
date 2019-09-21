package com.fadergs.futapp;

public class Jogador {
    private int id;
    private String nome;
    private int num_camisa;
    private int id_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum_camisa() {
        return num_camisa;
    }

    public void setNum_camisa(int num_camisa) {
        this.num_camisa = num_camisa;
    }

    public int getId_time() {
        return id_time;
    }

    public void setId_time(int id_time) {
        this.id_time = id_time;
    }

    @Override
    public String toString() {
        return "" + this.nome + " " + this.num_camisa;
    }
}
