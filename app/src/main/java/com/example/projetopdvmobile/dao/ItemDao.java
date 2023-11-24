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

import java.util.ArrayList;

public class ItemDao  implements IGenericDao<Item> {

    //Variavel responsavel por abrir conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    private String[]colunas = {"ID", "COD_PRODOTU" , "QTD_EST DESCRICAO", "VL_COMPRA", "VL_VENDA"};

    private String tabela = "ITEM";

    private Context context;

    private static ItemDao instancia;

    public static ItemDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new ItemDao(context);
        }else{
            return instancia;
        }


    }
    private ItemDao(Context context){
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR_BD", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();


    }

    public long insert(Item obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getId());
            valores.put(colunas[1], obj.getCod_prodotu());
            valores.put(colunas[2], obj.getQtd_est());
            valores.put(colunas[3], obj.getDescricao());
            valores.put(colunas[4], obj.getVl_compra());
            valores.put(colunas[5], obj.getVl_venda());
                
            return baseDados.insert(tabela, null, valores);


        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ClienteDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Item obj) {
        return 0;
    }

    @Override
    public long delete(Item obj) {
        return 0;
    }

    @Override
    public ArrayList<Item> getAll() {
        return null;
    }

    @Override
    public Item getById(int id) {
        return null;
    }


}
