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
import android.widget.Button;
import android.widget.ImageButton;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {
    private Button btSelectCliente;
    private ImageButton btAddCliente;
    private AlertDialog dialog;

    private View viewAlert;
    private Cliente clienteSelecionado;

    private ClienteController clienteController;
    private ClienteListAdapter clienteListAdapter;
    private ArrayList<Cliente> listaClientes;

    private int posCliente =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);
        setTitle("Cadastro de Pedido");

    }


    private void carregarClientes() {
        listaClientes = clienteController.retornarTodosClientes();
    }


}