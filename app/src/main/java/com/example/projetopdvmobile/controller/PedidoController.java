package com.example.projetopdvmobile.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.projetopdvmobile.dao.PedidoDao;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Item;
import com.example.projetopdvmobile.model.Pedido;

import java.util.ArrayList;
import java.util.Date;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public void salvarPedido(String descricaoPedido, Date dataCriacao, double vlTotal, Cliente cliente, ArrayList<Item> listaProdutos) {
        try {
            // Criar um novo pedido
            Pedido pedido = new Pedido();
            pedido.setDescricaopedido(descricaoPedido);
            pedido.setDataCriacao(dataCriacao);
            pedido.setVlTotal(vlTotal);
            pedido.setCliente(cliente);
            pedido.setListaProdutos(listaProdutos);

            // Salvar o pedido no banco de dados
            long resultado = PedidoDao.getInstancia(context).insert(pedido);

            if (resultado > 0) {
                Toast.makeText(context, "Pedido salvo com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Erro ao salvar o pedido", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Erro ao salvar o pedido", Toast.LENGTH_SHORT).show();
        }
    }


    public ArrayList<Pedido> retornarTodosClientes(){
        return PedidoDao.getInstancia(context).getAll();
    }
}
