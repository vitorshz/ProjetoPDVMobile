package com.example.projetopdvmobile.controller;

import android.content.Context;

import com.example.projetopdvmobile.dao.PedidoDao;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Pedido;

import java.util.ArrayList;
import java.util.Date;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public String salvarPedido(int numPedido, Date dataCriacao, Cliente cliente) {
        try {
            if (numPedido <= 0) {
                return "Informe um número de pedido válido!";
            }

            // Verifica se o número do pedido já está cadastrado
            Pedido pedidoExistente = PedidoDao.getInstancia(context).getById(numPedido);
            if (pedidoExistente != null) {
                return "O número do pedido já está cadastrado!";
            }

            // Cria um novo pedido
            Pedido novoPedido = new Pedido(numPedido, dataCriacao, cliente);

            // Insere o pedido no banco de dados
            long resultado = PedidoDao.getInstancia(context).insert(novoPedido);

            if (resultado != -1) {
                return null; // Sucesso
            } else {
                return "Erro ao salvar o pedido!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Erro ao salvar o pedido.";
        }
    }


    public ArrayList<Pedido> retornarTodosClientes(){
        return PedidoDao.getInstancia(context).getAll();
    }
}
