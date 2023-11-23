package com.example.projetopdvmobile.model;

import java.util.ArrayList;

public class Carrinho {

    private int idCarrinho;
    private int qtd;
    private int dataAdd;
    private ArrayList<Item> listaItens;

    public Carrinho(int idCarrinho, int qtd, int dataAdd, ArrayList<Item> listaItens) {
        this.idCarrinho = idCarrinho;
        this.qtd = qtd;
        this.dataAdd = dataAdd;
        this.listaItens = listaItens;
    }

    public Carrinho() {

    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(int dataAdd) {
        this.dataAdd = dataAdd;
    }

    public ArrayList<Item> getListaItens() {
        return listaItens;
    }

    public void setListaItens(ArrayList<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public void addItemCarrinho(){
        //implementar
    }

    public void atualizarQTD(){
        //implementar
    }
    public void verDetalheCarrinho(){
        //implementar
    }
    public void prossegCompra(){
        //implementar
    }
}
