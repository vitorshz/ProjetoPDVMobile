package com.example.projetopdvmobile.model;

public class Item {

    private int id, cod_produto, qtd_est;
    private String descricao;
    private double vl_compra, vl_venda;

    public Item() {
    }

    public Item(int id, int cod_produto, int qtd_est, String descricao, double vl_compra, double vl_venda) {
        this.id = id;
        this.cod_produto = cod_produto;
        this.qtd_est = qtd_est;
        this.descricao = descricao;
        this.vl_compra = vl_compra;
        this.vl_venda = vl_venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(int cod_prodotu) {
        this.cod_produto = cod_prodotu;
    }

    public int getQtd_est() {
        return qtd_est;
    }

    public void setQtd_est(int qtd_est) {
        this.qtd_est = qtd_est;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getVl_compra() {
        return vl_compra;
    }

    public void setVl_compra(double vl_compra) {
        this.vl_compra = vl_compra;
    }

    public double getVl_venda() {
        return vl_venda;
    }

    public void setVl_venda(double vl_venda) {
        this.vl_venda = vl_venda;
    }

}
