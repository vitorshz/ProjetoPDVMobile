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
import com.example.projetopdvmobile.model.Pedido;

import java.util.Date;

public class PedidoDao implements IGenericDao<Pedido> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;

    private String[] colunas = {"ID", "NUM_PEDIDO", "DATA_CRIACAO", "DATA_ENVIO", "ESTADO", "NUM_ENVIO", "CLIENTE_ID"};

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

            valores.put(colunas[1], obj.getNumPedido());
            valores.put(colunas[2], obj.getDataCriacao().getTime()); // Convertendo para milissegundos
            valores.put(colunas[3], obj.getDataEnvio() != null ? obj.getDataEnvio().getTime() : null); // Convertendo para milissegundos
            valores.put(colunas[4], obj.getEstado());
            valores.put(colunas[5], obj.getNumEnvio());
            valores.put(colunas[6], obj.getCliente().getId()); // Supondo que Cliente tenha uma coluna ID

            long result = baseDados.insert(tabela, null, valores);

            if (result != -1) {
                // Adicionando a atualização da lista após a inserção bem-sucedida
                Log.i("UNIPAR", "PedidoDao.insert() - Pedido inserido com sucesso");
                return result;
            } else {
                Log.e("UNIPAR", "ERRO: PedidoDao.insert() - Falha ao inserir o pedido");
            }
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: PedidoDao.insert() " + ex.getMessage());
        }
        return 0;
    }

    // Implemente os outros métodos do IGenericDao conforme necessário
    // (update, delete, getAll, getById)

    @Override
    public long update(Pedido obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNumPedido());

            String[]identificador = {String.valueOf(obj.getNumPedido())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pedido obj) {
        try{
            String[]identificador = {String.valueOf(obj.getNumPedido())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0] + " DESC");

            if (cursor.moveToFirst()) {
                do {
                    Pedido ped = new Pedido();
                    ped.setNumPedido(cursor.getInt(0));
                    ped.setDataCriacao(new Date(cursor.getLong(1)));
                    ped.setDataEnvio(new Date(cursor.getLong(2)));
                    ped.setEstado(cursor.getString(3));
                    ped.setNumEnvio(cursor.getInt(4));

                    // Recuperando o ID do cliente associado ao pedido
                    int clienteId = cursor.getInt(5);

                    // Agora você precisa buscar o cliente com o clienteId
                    Cliente clienteAssociado = ClienteDAO.getInstancia(context).getById(clienteId);

                    // Verifique se o cliente foi encontrado antes de definir no pedido
                    if (clienteAssociado != null) {
                        ped.setCliente(clienteAssociado);
                    }

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
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Pedido ped = new Pedido();
                ped.setNumPedido(cursor.getInt(0));
                ped.setDataCriacao(new Date(cursor.getLong(1)));
                ped.setDataEnvio(new Date(cursor.getLong(2)));
                ped.setEstado(cursor.getString(3));
                ped.setNumEnvio(cursor.getInt(4));

                // Recuperando o ID do cliente associado ao pedido
                int clienteId = cursor.getInt(5);

                return ped;
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.getById() "+ex.getMessage());
        }
        return null;
    }
}

