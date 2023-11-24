package com.example.projetopdvmobile.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE (ID INTEGER PRIMARY KEY AUTOINCREMENT ,CPF VARCHAR(11), NOME VARCHAR(100)," +
                "EMAIL VARCHAR(100),TELEFONE VARCHAR(15))");

        sqLiteDatabase.execSQL("CREATE TABLE ITEM (ID INTEGER, COD_PRODOTU INTEGER , QTD_ES INTEGER," +
                "DESCRICAO VARCHAR(200), VL_COMPRA REAL, VL_VENDA REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
