package com.example.projetopdvmobile.model;

public class GerarId {

    private int proximoId = 1;

    public int gerarProximoId() {
        return proximoId++;
    }


}
