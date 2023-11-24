package com.example.projetopdvmobile.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ItemListAdapter;
import com.example.projetopdvmobile.controller.ItemController;
import com.example.projetopdvmobile.model.GerarId;
import com.example.projetopdvmobile.model.Item;

import java.util.ArrayList;

public class CadastroItemActivity extends AppCompatActivity {

    private EditText edDescProduto;
    private EditText edValorCompra;
    private EditText edValorVenda;
    private EditText edCodBarra;

    private EditText edQtdEstq;
    private Button btCadastrarProduto;
    private ItemController controller;
    private AlertDialog dialog;
    private RecyclerView rvItems;
    private View viewAlert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item_venda);
        setTitle("Cadastro de Item");


        controller = new ItemController(this);

        edDescProduto = findViewById(R.id.edDescProduto);
        edValorCompra = findViewById(R.id.edValorCompra);
        edValorVenda = findViewById(R.id.edValorVenda);
        edCodBarra = findViewById(R.id.edCodBarra);
        edQtdEstq = findViewById(R.id.edQtdEstq);


        btCadastrarProduto = findViewById(R.id.btCadastrarProduto);
        rvItems = findViewById(R.id.rvItems);
        btCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
        atualizarListaItem();

    }
    private void salvarDados(){
        GerarId gerar = new GerarId();
        int idanterior = 0;

// Gere o primeiro ID
        int idgerado = gerar.gerarProximoId();

// Verifique se o ID gerado é o mesmo que o ID anterior
        while (idanterior == idgerado) {
            // Gere um novo ID
            idgerado = gerar.gerarProximoId();
        }

// Atualize o ID anterior para o ID gerado no final do loop
          idanterior = idgerado;

        String retorno = controller.salvarItem(idgerado,
                edDescProduto.getText().toString(),
                Integer.parseInt(edQtdEstq.getText().toString()),
                Integer.parseInt(edCodBarra.getText().toString()),
                Double.parseDouble(edValorCompra.getText().toString()),
                Double.parseDouble(edValorVenda.getText().toString()));



        if(retorno != null){
            if(retorno.contains("ID")){
                edCodBarra.setError(retorno);
                edCodBarra.requestFocus();
            }

            if(retorno.contains("COD_PRODOTU")){
                edCodBarra.setError(retorno);
                edCodBarra.requestFocus();
            }
            if(retorno.contains("DESCRICAO")){
                edDescProduto.setError(retorno);
                edDescProduto.requestFocus();
            }
            if(retorno.contains("QTD_EST")){
                edQtdEstq.setError(retorno);
                edQtdEstq.requestFocus();
            }
            if(retorno.contains("VL_COMPRA")){
                edValorCompra.setError(retorno);
                edValorCompra.requestFocus();
            }
            if(retorno.contains("VL_VENDA")){
                edValorVenda.setError(retorno);
                edValorVenda.requestFocus();
            }

        }else{
            Toast.makeText(this,
                    "Item salvo com sucesso!",
                    Toast.LENGTH_LONG).show();
            dialog.dismiss();
            atualizarListaItem();
        }

    }
    private void atualizarListaItem() {
        ArrayList<Item> listaItem = controller.retornarTodosItens();
        ItemListAdapter adapter = new ItemListAdapter(listaItem, this);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setAdapter(adapter);

    }
}