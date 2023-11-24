package com.example.projetopdvmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projetopdvmobile.helper.SQLiteDataHelper;

import java.util.ArrayList;

import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Item;
import com.example.projetopdvmobile.model.Pedido;

import java.util.Collection;
import java.util.Date;

public class PedidoDao implements IGenericDao<Pedido>  {


    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;

    private String[] colunas = {"ID", "DESCRICAO_PEDIDO", "DATA_CRIACAO", "VL_TOTAL", "CLIENTE_ID"};
    private String tabela = "PEDIDO";

    private Context context;

    private static PedidoDao instancia;

    public static PedidoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new PedidoDao(context);
        } else {
            return instancia;
        }
    }

    private PedidoDao(Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context, "UNIPAR_BD", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();

            valores.put(colunas[1], obj.getDescricaopedido());
            valores.put(colunas[2], obj.getDataCriacao().getTime()); // Converta a data para milissegundos
            valores.put(colunas[3], obj.getVlTotal());
            valores.put(colunas[4], obj.getCliente().getId());

            long resultado = baseDados.insert(tabela, null, valores);

            if (resultado != -1) {
                // Se a inserção foi bem-sucedida, obtenha o ID gerado
                Cursor cursor = baseDados.rawQuery("SELECT last_insert_rowid()", null);
                if (cursor != null && cursor.moveToFirst()) {
                    obj.setNumPedido(cursor.getInt(0));
                    cursor.close();
                }
                // Adicione os itens à tabela de itens (se existirem)
                insertItens(obj);
            } else {
                Log.e("UNIPAR", "ERRO: PedidoDao.insert() - Falha ao inserir o pedido");
            }

            return resultado;
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.insert() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDescricaopedido());
            valores.put(colunas[2], obj.getDataCriacao().getTime());
            valores.put(colunas[3], obj.getVlTotal());
            valores.put(colunas[4], obj.getCliente().getId());

            String[] identificador = {String.valueOf(obj.getNumPedido())};
            return baseDados.update(tabela, valores, colunas[0] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[] identificador = {String.valueOf(obj.getNumPedido())};
            return baseDados.delete(tabela, colunas[0] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas, null, null, null, null, colunas[0] + " DESC");

            if (cursor.moveToFirst()) {
                do {
                    Pedido ped = new Pedido();
                    ped.setNumPedido(cursor.getInt(0));
                    ped.setDescricaopedido(cursor.getString(1));
                    ped.setDataCriacao(new Date(cursor.getLong(2)));
                    ped.setVlTotal(cursor.getDouble(3));

                    // Adicione os itens relacionados ao pedido
                    ped.setListaProdutos(getItensByPedidoId(ped.getNumPedido()));

                    // Adicione o cliente relacionado ao pedido (assumindo que você tenha o ClienteDao)
                    Cliente cliente = ClienteDAO.getInstancia(context).getById(cursor.getInt(4));
                    ped.setCliente(cliente);

                    lista.add(ped);

                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Pedido getById(int id) {
        return null;
    }

    private ArrayList<Item> getItensByPedidoId(int pedidoId) {
        ArrayList<Item> itens = new ArrayList<>();
        try {
            String[] colunasItens = {"ID", "COD_PRODUTO", "QTD_EST", "DESCRICAO", "VL_COMPRA", "VL_VENDA"};
            String tabelaItens = "ITEM";
            String whereClause = "PEDIDO_ID = ?";
            String[] whereArgs = {String.valueOf(pedidoId)};

            Cursor cursor = baseDados.query(tabelaItens, colunasItens, whereClause, whereArgs, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setCod_produto(cursor.getInt(1));
                    item.setQtd_est(cursor.getInt(2));
                    item.setDescricao(cursor.getString(3));
                    item.setVl_compra(cursor.getDouble(4));
                    item.setVl_venda(cursor.getDouble(5));

                    itens.add(item);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.getItensByPedidoId() " + ex.getMessage());
        }
        return itens;
    }

    // Adicione métodos para inserir, atualizar e excluir itens conforme necessário
    private void insertItens(Pedido pedido) {
        for (Item item : pedido.getListaProdutos()) {
            ContentValues valores = new ContentValues();

            valores.put("PEDIDO_ID", pedido.getNumPedido());
            valores.put("ID", item.getCod_produto());
            valores.put("QTD_EST", item.getQtd_est());
            valores.put("DESCRICAO", item.getDescricao());
            valores.put("VL_COMPRA", item.getVl_compra());
            valores.put("VL_VENDA", item.getVl_venda());

            baseDados.insert("ITEM", null, valores);
        }
    }

    public Collection<String> buscarSugestoesDoBancoDeDados() {
        Collection<String> sugestoes = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = openHelper.getReadableDatabase();

            String query = "SELECT DESCRICAO FROM ITEM;";
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    String nomeProduto = cursor.getString(2);
                    sugestoes.add(nomeProduto);
                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("UNIPAR", "Erro ao buscar sugestões do banco de dados: " + ex.getMessage());
        } finally {
            // Certifique-se de fechar o cursor e o banco de dados para evitar vazamento de recursos
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        return sugestoes;
    }

}

