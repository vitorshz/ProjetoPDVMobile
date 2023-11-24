package com.example.projetopdvmobile.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projetopdvmobile.R;

public class GerenciadorPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}