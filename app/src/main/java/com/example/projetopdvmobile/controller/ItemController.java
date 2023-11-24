package com.example.projetopdvmobile.controller;

import android.content.Context;


import com.example.projetopdvmobile.dao.ItemDao;
import com.example.projetopdvmobile.model.Item;

import java.util.ArrayList;

public class ItemController {

    private Context context;

    public ItemController(Context context) {
        this.context = context;
    }

    public String salvarItem(int cod_produto, int qtd_est,String descricao, double vl_compra, double vl_venda) {
        try {

            if (cod_produto <= 0) {
                return "Informe um código válido para o item!";
            }
            if (qtd_est <= 0) {
                return "Informe uma quantidade em estoque válida!";
            }
            if (descricao == null || descricao.isEmpty()) {
                return "Informe uma descrição para o item";
            }
            if (vl_compra <= 0) {
                return "Informe um valor de compra válido para o item!";
            }
            if (vl_venda <= 0) {
                return "Informe um valor de venda válido para o item!";
            }

            Item item = ItemDao.getInstancia(context).getById((int) cod_produto);

            if (item != null) {
                return "O código do produto (" + cod_produto + ") já está cadastrado!";
            } else {
                item = new Item();
                item.setCod_produto((int) cod_produto);
                item.setQtd_est(qtd_est);
                item.setDescricao(descricao);
                item.setVl_compra(vl_compra);
                item.setVl_venda(vl_venda);

                ItemDao.getInstancia(context).insert(item);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Erro ao gravar item.";
        }
        return null;
    }

    /**
     * Retorna todos os itens cadastrados no banco
     *
     * @return ArrayList<Item>
     */
    public ArrayList<Item> retornarTodosItens() {
        return ItemDao.getInstancia(context).getAll();
    }
}




