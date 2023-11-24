package com.example.projetopdvmobile.model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int idPedido;
    private Date dataPedido;
    private Cliente cliente;
    private ArrayList<ItemVenda> itensVendidos;
    private double totalPedido;

    // Construtor, getters e setters...

    public Pedido() {
    }



    public void adicionarItem(ItemVenda itemVenda) {
        if (itensVendidos == null) {
            itensVendidos = new ArrayList<>();
        }

        itensVendidos.add(itemVenda);
        calcularTotalPedido();
    }

    private void calcularTotalPedido() {
        totalPedido = 0;
        for (ItemVenda itemVenda : itensVendidos) {
            totalPedido += itemVenda.getSubtotal();
        }
    }

    public void finalizarPedido() {
        // Adicionar lógica para finalizar o pedido,
        // como salvar no banco de dados, enviar para o sistema de pagamento, etc.
        // Este é um exemplo simples.
        System.out.println("Pedido finalizado! Total: " + totalPedido);
    }

}
