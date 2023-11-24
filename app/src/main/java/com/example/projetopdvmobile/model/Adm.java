package com.example.projetopdvmobile.model;

public class Adm {
    private int cpnj;
    private int nfe;
    private String nome;

    public int getCpnj() {
        return cpnj;
    }

    public void setCpnj(int cpnj) {
        this.cpnj = cpnj;
    }

    public int getNfe() {
        return nfe;
    }

    public void setNfe(int nfe) {
        this.nfe = nfe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Adm(int cpnj, int nfe, String nome) {
        this.cpnj = cpnj;
        this.nfe = nfe;
        this.nome = nome;
    }

    public Adm() {
    }
}
