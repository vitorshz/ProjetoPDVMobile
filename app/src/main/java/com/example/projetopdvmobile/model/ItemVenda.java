package com.example.projetopdvmobile.model;

import java.util.ArrayList;

public class ItemVenda {
    private int idCarrinho;
    private int dataAdd;
    private ArrayList<ItemVenda> listaItens; // O item vendido
    private Item item;
    private int quantidade; // A quantidade vendida
    private double subtotal;

    public ItemVenda() {
    }

    public ItemVenda(int idCarrinho, int dataAdd, ArrayList<ItemVenda> listaItens, Item item, int quantidade, double subtotal) {
        this.idCarrinho = idCarrinho;
        this.dataAdd = dataAdd;
        this.listaItens = listaItens;
        this.item = item;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public int getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(int dataAdd) {
        this.dataAdd = dataAdd;
    }

    public ArrayList<ItemVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(ArrayList<ItemVenda> listaItens) {
        this.listaItens = listaItens;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    // Construtor e outros métodos da classe...

    public void addItemCarrinho(Item item, int quantidade) {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setItem(item);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setSubtotal(item.getVl_venda() * quantidade);

        listaItens.add(itemVenda);

        // Adicionar lógica adicional, se necessário.
    }

    public void atualizarQTD(Item item, int novaQuantidade) {
        for (ItemVenda itemVenda : listaItens) {
            if (itemVenda.getItem().equals(item)) {
                itemVenda.setQuantidade(novaQuantidade);
                itemVenda.setSubtotal(item.getVl_venda() * novaQuantidade);
                break;
            }
        }

        // Adicionar lógica adicional, se necessário.
    }

    public void verDetalheCarrinho() {
        for (ItemVenda itemVenda : listaItens) {
            System.out.println("Item: " + itemVenda.getItem().getDescricao());
            System.out.println("Quantidade: " + itemVenda.getQuantidade());
            System.out.println("Subtotal: " + itemVenda.getSubtotal());
            System.out.println("-----------------------------");
        }

    }
    public void prossegCompra(){
        //implementar
    }
}
