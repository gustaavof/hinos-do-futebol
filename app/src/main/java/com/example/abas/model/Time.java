package com.example.abas.model;

import java.io.Serializable;

public class Time implements Serializable {

    private int escudo;
    private String hino;
    private String nome;

    public Time(int escudo, String hino, String nome) {
        this.escudo = escudo;
        this.hino = hino;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getEscudo() {
        return escudo;
    }

    public String getHino() {
        return hino;
    }
}
