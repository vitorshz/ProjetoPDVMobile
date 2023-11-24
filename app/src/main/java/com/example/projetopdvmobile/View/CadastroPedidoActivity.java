package com.example.projetopdvmobile.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.controller.ItemController;
import com.example.projetopdvmobile.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {
    private TextView tvClienteSelecionado;
    private AutoCompleteTextView actProduto;
    private EditText edQtdProduto;
    private RecyclerView rvItensCarrinho;
    private Button btAddProduto;
    private TextView tvValorTotalPedido;
    private Button btFinalizarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Cadastro de Pedido");


        tvClienteSelecionado = findViewById(R.id.tvClienteSelecionado);
        actProduto = findViewById(R.id.actProduto);
        edQtdProduto = findViewById(R.id.edQtdProduto);
        rvItensCarrinho = findViewById(R.id.rvItensCarrinho);
        btAddProduto = findViewById(R.id.btAddProduto);
        tvValorTotalPedido = findViewById(R.id.tvValorTotalPedido);
        btFinalizarPedido = findViewById(R.id.btFinalizarPedido);


    }


}