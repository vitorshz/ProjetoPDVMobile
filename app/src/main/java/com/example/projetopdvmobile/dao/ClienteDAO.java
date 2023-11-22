package com.example.projetopdvmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projetopdvmobile.helper.SQLiteDataHelper;
import com.example.projetopdvmobile.model.Cliente;

import java.util.ArrayList;

public class ClienteDAO implements IGenericDao<Cliente> {

    //Variavel responsavel por abrir conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[]colunas = {"ID","CPF","NOME","EMAIL","TELEFONE"};

    //nome da tabela
    private String tabela = "CLIENTE";

    //Contexto (view)
    private Context context;

    private static ClienteDAO instancia;

    public static ClienteDAO getInstancia(Context context){
        if(instancia == null){
            return instancia = new ClienteDAO(context);
        }else{
            return instancia;
        }
    }

    private ClienteDAO(Context context){
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR_BD", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();


    }

    @Override
    public long insert(Cliente obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getId());
            valores.put(colunas[1], obj.getCpf()); //
            valores.put(colunas[2], obj.getNome()); //
            valores.put(colunas[3], obj.getEmail()); //
            valores.put(colunas[4], obj.getTelefone()); //

            return baseDados.insert(tabela, null, valores);


        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Cliente obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());

            String[]identificador = {String.valueOf(obj.getCpf())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: AlunoDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Cliente obj) {
        try{
            String[]identificador = {String.valueOf(obj.getId())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: AlunoDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Cliente> getAll() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Cliente cliente = new Cliente();
                    cliente.setId(cursor.getInt(0));
                    cliente.setCpf(cursor.getString(1));
                    cliente.setNome(cursor.getString(2));
                    cliente.setEmail(cursor.getString(3));
                    cliente.setTelefone(cursor.getString(4));

                    lista.add(cliente);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Cliente getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Cliente cliente = new Cliente();
                cliente.setId(cursor.getInt(0));
                cliente.setCpf(cursor.getString(1));
                cliente.setNome(cursor.getString(2));
                cliente.setEmail(cursor.getString(3));
                cliente.setTelefone(cursor.getString(4));

                return cliente;
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: AlunoDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
