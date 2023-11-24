package com.example.projetopdvmobile.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.adapter.PedidoListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.controller.ItemController;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView actProduto;
    private EditText edQtdProduto;
    private Button btAddProduto, btFinalizarPedido;
    private RecyclerView rvItensCarrinho;
    private PedidoListAdapter pedidoListAdapter;
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        // Inicializar componentes
        actProduto = findViewById(R.id.actProduto);
        edQtdProduto = findViewById(R.id.edQtdProduto);
        btAddProduto = findViewById(R.id.btAddProduto);
        btFinalizarPedido = findViewById(R.id.btFinalizarPedido);
        rvItensCarrinho = findViewById(R.id.rvItensCarrinho);

        // Configurar LayoutManager para o RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvItensCarrinho.setLayoutManager(layoutManager);

        // Criar e configurar o adaptador do RecyclerView
        pedidoListAdapter = new PedidoListAdapter(listaPedidos, this);
        rvItensCarrinho.setAdapter(pedidoListAdapter);

        // Configurar o AutoCompleteTextView com um adaptador de sugestões
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, getSugestoesProdutos());
        actProduto.setAdapter(autoCompleteAdapter);

        // Configurar a ação do botão "Adicionar Produto"
        btAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementar lógica para adicionar produto à lista
                adicionarProduto();
            }
        });

        // Configurar a ação do botão "Finalizar Pedido"
        btFinalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementar lógica para finalizar o pedido
                finalizarPedido();
            }
        });
    }

    private ArrayList<String> getSugestoesProdutos() {
        // Implementar lógica para obter sugestões de produtos (pode ser uma lista fixa, consulta ao banco de dados, etc.)
        ArrayList<String> sugestoes = new ArrayList<>();
        // Adicionar produtos à lista de sugestões
        sugestoes.add("Produto 1");
        sugestoes.add("Produto 2");
        // Adicione mais produtos conforme necessário
        return sugestoes;
    }

    private void adicionarProduto() {
        // Implementar lógica para adicionar produto à lista
        // Obter valores do AutoCompleteTextView e EditText
        String produto = actProduto.getText().toString();
        String qtd = edQtdProduto.getText().toString();

        // Validação de dados
        if (TextUtils.isEmpty(produto) || TextUtils.isEmpty(qtd)) {
            // Mostrar mensagem de erro se algum campo estiver vazio
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Adicionar o produto à lista de produtos do pedido
        // Você pode precisar criar uma classe Produto para representar um item na lista
        // e adicionar esse produto à lista de produtos do pedido

        // Atualizar o adaptador do RecyclerView para refletir as alterações na lista de produtos
        pedidoListAdapter.notifyDataSetChanged();

        // Limpar os campos após adicionar o produto
        actProduto.setText("");
        edQtdProduto.setText("");
    }

    private void finalizarPedido() {
        // Implementar lógica para finalizar o pedido
        // Pode envolver a criação de um novo objeto Pedido, a adição dos produtos selecionados, etc.

        // Atualizar a interface do usuário conforme necessário

        // Limpar a lista de produtos do pedido
        listaPedidos.clear();
        pedidoListAdapter.notifyDataSetChanged();
    }
}
