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

    private String[]colunas = {"ID", "COD_PRODOTU" , "QTD_EST", "DESCRICAO", "VL_COMPRA", "VL_VENDA"};


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

            valores.put(colunas[1], obj.getCod_prodotu());
            valores.put(colunas[2], obj.getQtd_est());
            valores.put(colunas[3], obj.getDescricao());
            valores.put(colunas[4], obj.getVl_compra());
            valores.put(colunas[5], obj.getVl_venda());
                
            return baseDados.insert(tabela, null, valores);


        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ItemDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Item obj) {

        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getId());

            String[]indentificador = {String.valueOf(obj.getCod_prodotu())};

            return  baseDados.update(tabela, valores,
                    colunas[0]+ "= ?", indentificador);

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ItemDao.udapte()"+ex.getMessage());
        }

        return 0;
    }

    @Override
    public long delete(Item obj) {

        try{

            String[]indentificador = {String.valueOf(obj.getId())};

            return  baseDados.delete(tabela,
                    colunas[0]+ "= ?", indentificador);

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ItemDao.delete()"+ex.getMessage());
        }

        return 0;
    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> lista = new ArrayList<>();
        try{

            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]);
            if(cursor.moveToFirst()){
                do{
                    Item item = new Item();
                    item.setId(cursor.getInt(0));
                    item.setCod_prodotu(cursor.getInt(1));
                    item.setQtd_est(cursor.getInt(2));
                    item.setDescricao(cursor.getString(3));
                    item.setVl_compra(cursor.getInt(4));
                    item.setVl_venda(cursor.getInt(5));

                    lista.add(item);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ItemDao.getAll()"+ex.getMessage());
        }


        return null;
    }

    @Override
    public Item getById(int id) {


        try{
            String[] indetificador = {String.valueOf(id)};

            Cursor cursor = baseDados.query(tabela, colunas, colunas[0]+"= ?",
                    indetificador, null, null, null);

            if(cursor.moveToFirst()){
                Item item  = new Item();
                item.setId(cursor.getInt(0));
                item.setCod_prodotu(cursor.getInt(1));
                item.setQtd_est(cursor.getInt(2));
                item.setDescricao(cursor.getString(3));
                item.setVl_compra(cursor.getInt(4));
                item.setVl_venda(cursor.getInt(5));

                return  item;

            }


        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: ItemDao.getById()"+ex.getMessage());
        }
        return null;


    }


}
