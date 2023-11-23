package com.example.projetopdvmobile.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.GerarId;

import java.util.ArrayList;

public class CadastroClienteActivity extends AppCompatActivity {
    private EditText edNomeCliente;
    private EditText edCPFCliente;
    private EditText edTelefoneCliente;
    private EditText edEmailCliente;
    private Button btCadastrarCliente;
    private ClienteController controller;
    private AlertDialog dialog;
    private RecyclerView rvClientes;
    private View viewAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        setTitle("Cadastro de Cliente");

        controller = new ClienteController(this);

        edCPFCliente = findViewById(R.id.edCPFCliente);
        edNomeCliente = findViewById(R.id.edNomeCliente);
        edTelefoneCliente = findViewById(R.id.edTelefoneCliente);
        edEmailCliente = findViewById(R.id.edEmailCliente);

        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        rvClientes = findViewById(R.id.rvClientes);
        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });
        atualizarListaCliente();

    }

    private void salvarDados(){
        GerarId gerar = new GerarId();
        int idgerado = gerar.gerarProximoId();

        String retorno = controller.salvarCliente(idgerado,edCPFCliente.getText().toString(),
                edNomeCliente.getText().toString(),
                edEmailCliente.getText().toString(),
                edTelefoneCliente.getText().toString());

        if(retorno != null){
            if(retorno.contains("CPF")){
                edCPFCliente.setError(retorno);
                edCPFCliente.requestFocus();
            }
            if(retorno.contains("NOME")){
                edNomeCliente.setError(retorno);
                edNomeCliente.requestFocus();
            }
            if(retorno.contains("TELEFONE")){
                edTelefoneCliente.setError(retorno);
                edTelefoneCliente.requestFocus();
            }
            if(retorno.contains("EMAIL")){
                edEmailCliente.setError(retorno);
                edEmailCliente.requestFocus();
            }

        }else{
            Toast.makeText(this,
                    "Cliente salvo com sucesso!",
                    Toast.LENGTH_LONG).show();
            dialog.dismiss();
            atualizarListaCliente();
        }

    }
    private void atualizarListaCliente() {
        ArrayList<Cliente> listaClientes = controller.retornarTodosClientes();
        ClienteListAdapter adapter = new ClienteListAdapter(listaClientes, this);
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        rvClientes.setAdapter(adapter);

    }
}