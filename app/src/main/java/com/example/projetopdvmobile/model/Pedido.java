package com.example.projetopdvmobile.model;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int numPedido;
    private Date dataCriacao;
    private Date dataEnvio;
    private String estado;
    private int numEnvio;
    private Cliente cliente;
    private ArrayList<ItemVenda> itens;

    public Pedido(int numPedido, Date dataCriacao, Cliente cliente) {
        this.numPedido = numPedido;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Pedido() {
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumEnvio() {
        return numEnvio;
    }

    public void setNumEnvio(int numEnvio) {
        this.numEnvio = numEnvio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemVenda> itens) {
        this.itens = itens;
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public void finalizarPedido() {
        // Lógica para finalizar o pedido, gerar NFE, etc.
        NotaFiscalEletronica nfe = new NotaFiscalEletronica(this);
        nfe.emitirNotaFiscal();
    }


}
