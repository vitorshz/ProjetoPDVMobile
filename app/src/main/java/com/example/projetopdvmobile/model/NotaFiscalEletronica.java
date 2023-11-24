package com.example.projetopdvmobile.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotaFiscalEletronica {
    private Pedido pedido;

    public NotaFiscalEletronica(Pedido pedido) {
        this.pedido = pedido;
    }

    public void emitirNotaFiscal() {
        // Lógica para emitir a nota fiscal eletrônica
        System.out.println("Emitindo Nota Fiscal Eletrônica para o pedido: " + pedido.getNumPedido());
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("CPF do Cliente: " + pedido.getCliente().getCpf());
        System.out.println("Data de Criação: " + formatarData(pedido.getDataCriacao()));
        System.out.println("Itens do Pedido:");

        for (ItemVenda item : pedido.getItens()) {
            System.out.println("   Produto: " + item.getItem().getDescricao());
            System.out.println("   Quantidade: " + item.getQuantidade());
            System.out.println("   Subtotal: " + item.getSubtotal());
        }

        System.out.println("Total do Pedido: " + calcularTotalPedido());
        System.out.println("Nota Fiscal Eletrônica emitida com sucesso.");
    }

    private String formatarData(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(data);
    }

    private double calcularTotalPedido() {
        double total = 0.0;

        for (ItemVenda item : pedido.getItens()) {
            total += item.getSubtotal();
        }

        return total;
    }
}