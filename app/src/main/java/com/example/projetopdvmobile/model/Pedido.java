package com.example.projetopdvmobile.model;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private int numPedido;
    private String descricaopedido;
    private Date dataCriacao;
    private double vlTotal ;
    private Cliente cliente;
    private ArrayList<Item>  listaProdutos;

    public Pedido(int numPedido, String descricaopedido, Date dataCriacao, double vlTotal, Cliente cliente, ArrayList<Item> listaProdutos) {
        this.numPedido = numPedido;
        this.descricaopedido = descricaopedido;
        this.dataCriacao = dataCriacao;
        this.vlTotal = vlTotal;
        this.cliente = cliente;
        this.listaProdutos = listaProdutos;
    }
    public Pedido(){}

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public String getDescricaopedido() {
        return descricaopedido;
    }

    public void setDescricaopedido(String descricaopedido) {
        this.descricaopedido = descricaopedido;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Item> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Item> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }



}
