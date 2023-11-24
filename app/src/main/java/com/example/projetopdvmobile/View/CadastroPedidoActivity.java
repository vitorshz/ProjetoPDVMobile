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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.adapter.ClientePedidoAdapter;
import com.example.projetopdvmobile.adapter.PedidoListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.controller.ItemController;
import com.example.projetopdvmobile.controller.PedidoController;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Item;
import com.example.projetopdvmobile.model.Pedido;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {

    private AutoCompleteTextView actProduto;
    private EditText edQtdProduto;
    private Button btAddProduto, btFinalizarPedido, btSelectCliente;
    private RecyclerView rvItensCarrinho;
    private PedidoListAdapter pedidoListAdapter;
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private ArrayList<Item> listaItens = new ArrayList<>();
    private AlertDialog dialogSelecaoCliente;
    private View viewAlert;
    private ClienteController controller;
    private ClientePedidoAdapter clientePedidoAdapter;
    private PedidoController pedidoController;
    private TextView tvClienteSelecionado, tvValorTotalPedido;
    private Cliente clienteSelecionado;

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
        btSelectCliente = findViewById(R.id.btSelectCliente);
        tvClienteSelecionado = findViewById(R.id.tvClienteSelecionado);
        tvValorTotalPedido = findViewById(R.id.tvValorTotalPedido);
        setTitle("Cadasto de Pedidos");

        pedidoController = new PedidoController(this);

        // Configurar LayoutManager para o RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvItensCarrinho.setLayoutManager(layoutManager);

        pedidoListAdapter = new PedidoListAdapter(listaPedidos, new PedidoListAdapter.OnExcluirPedidoClickListener() {
            @Override
            public void onExcluirPedidoClick(int position, int itemIndex) {
                // Lógica para excluir o item do pedido
                excluirItemDoPedido(position, itemIndex);
            }
        });
        rvItensCarrinho.setAdapter(pedidoListAdapter);



        // Configurar o AutoCompleteTextView com um adaptador de sugestões
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, getSugestoesProdutos());
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

        // Configurar a ação do botão "Selecionar Cliente"
        btSelectCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implementar lógica para mostrar o diálogo de seleção de cliente
                mostrarDialogSelecaoCliente();
            }
        });

    }
    private void excluirItemDoPedido(int pedidoIndex, int itemIndex) {
        // Verifica se a posição do pedido é válida
        if (pedidoIndex >= 0 && pedidoIndex < listaPedidos.size()) {
            Pedido pedido = listaPedidos.get(pedidoIndex);

            // Verifica se o índice do item é válido
            if (itemIndex >= 0 && itemIndex < pedido.getListaProdutos().size()) {
                // Remove o item do pedido
                pedido.getListaProdutos().remove(itemIndex);

                // Notifica o adaptador que os dados foram alterados
                pedidoListAdapter.notifyDataSetChanged();

                // Atualiza o valor total do pedido e qualquer outra lógica necessária
                atualizarValorTotalPedido(pedido);
            }
        }
    }

    private void atualizarValorTotalPedido(Pedido pedido) {
        // Obtemos a lista de itens do pedido
        ArrayList<Item> itens = pedido.getListaProdutos();

        // Inicializamos o valor total como 0.0
        double valorTotal = 0.0;

        // Percorremos a lista de itens e somamos o valor total
        for (Item item : itens) {
            // Calculamos o valor total do item (quantidade * valor unitário)
            double valorItem = item.getQtd_est() * item.getVl_venda();

            // Somamos ao valor total do pedido
            valorTotal += valorItem;
        }

        // Formatamos o valor total como uma string formatada (por exemplo, R$ #,##0.00)
        String valorTotalFormatado = new DecimalFormat("R$ #,##0.00").format(valorTotal);

        // Atualiza a exibição do valor total na interface do usuário
        tvValorTotalPedido.setText(valorTotalFormatado);
    }

    private void finalizarPedido() {
    }

    private void mostrarDialogSelecaoCliente() {
        // Carregando o arquivo XML do layout
        View viewDialog = getLayoutInflater().inflate(R.layout.dialog_list_clientes, null);

        // Inicializando a RecyclerView
        RecyclerView rvClientes = viewDialog.findViewById(R.id.rvClientes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvClientes.setLayoutManager(layoutManager);

        // Obtendo a lista de clientes do banco de dados ou de onde você armazena
        ArrayList<Cliente> listaClientes = obterListaClientes();

        // Criando e configurando o adaptador
        ClientePedidoAdapter clienteListAdapter = new ClientePedidoAdapter(listaClientes, new ClientePedidoAdapter.OnAddClientePedidoClickListener() {
            @Override
            public void onAddClientePedidoClick(int position) {

                Cliente clienteSelecionado = listaClientes.get(position);
                // Atualizar a TextView com o nome do cliente selecionado
                tvClienteSelecionado.setText(" " + clienteSelecionado.getNome());

                Toast.makeText(CadastroPedidoActivity.this, "Cliente selecionado: " + clienteSelecionado.getNome(), Toast.LENGTH_SHORT).show();

                // Fechar o diálogo após a seleção do cliente
                if (dialogSelecaoCliente != null && dialogSelecaoCliente.isShowing()) {
                    dialogSelecaoCliente.dismiss();
                }
            }
        });

        // Atualizar a lista de clientes no adaptador
        clienteListAdapter.setListaClientes(listaClientes);

        rvClientes.setAdapter(clienteListAdapter);

        // Criando o AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione um Cliente");
        builder.setView(viewDialog);
        builder.setPositiveButton("Fechar", null);

        // Criando e exibindo o diálogo
        dialogSelecaoCliente = builder.create();
        dialogSelecaoCliente.show();
    }


    private ArrayList<Cliente> obterListaClientes() {
        // Crie uma instância do ClienteController passando o contexto
        ClienteController clienteController = new ClienteController(this);

        // Chame o método que retorna todos os clientes do banco de dados
        return clienteController.retornarTodosClientes();
    }

    private ArrayList<String> getSugestoesProdutos() {
        ArrayList<String> sugestoes = new ArrayList<>();

        // Obtém sugestões do banco de dados, por exemplo, usando um controller
        ItemController itemController = new ItemController(this);
        ArrayList<Item> todosItens = itemController.retornarTodosItens();

        // Adiciona os nomes dos produtos às sugestões
        for (Item item : todosItens) {
            sugestoes.add(item.getDescricao());
        }

        return sugestoes;
    }


    private void adicionarProduto() {
        // Recuperar o nome do produto do AutoCompleteTextView
        String nomeProduto = actProduto.getText().toString().trim();

        // Recuperar a quantidade do produto do EditText
        String quantidadeProdutoStr = edQtdProduto.getText().toString().trim();



        // Verificar se o nome do produto e a quantidade não estão vazios
        if (!nomeProduto.isEmpty() && !quantidadeProdutoStr.isEmpty()) {
            // Converter a quantidade para um valor numérico (por exemplo, inteiro)
            int quantidadeProduto = Integer.parseInt(quantidadeProdutoStr);

            // Criar um objeto Item com as informações fornecidas
            Item item = new Item();
            item.setDescricao(nomeProduto);
            item.setQtd_est(quantidadeProduto);

            Double valorDeVendaDoProduto = item.getVl_venda();
            item.setVl_venda(valorDeVendaDoProduto);

            // Adicionar o item à lista de itens
            listaItens.add(item);

            // Atualizar a lista de itens do pedido
            Pedido pedidoAtual = new Pedido();
            pedidoAtual.setListaProdutos(listaItens);

            // Adicionar o pedido à lista de pedidos
            listaPedidos.add(pedidoAtual);

            // Notificar o adaptador sobre a alteração
            pedidoListAdapter.notifyDataSetChanged();

            // Exemplo: exibir uma mensagem de confirmação
            Toast.makeText(this, "Item adicionado: " + nomeProduto + " (Quantidade: " + quantidadeProduto + ")", Toast.LENGTH_SHORT).show();

            // Limpar os campos após adicionar o item (opcional)
            actProduto.setText("");
            edQtdProduto.setText("");

            // Atualizar o valor total do pedido
            atualizarValorTotalPedido(pedidoAtual);
        } else {
            // Exibir uma mensagem de erro se algum campo estiver vazio
            Toast.makeText(this, "Preencha todos os campos do item", Toast.LENGTH_SHORT).show();
        }
    }

}
