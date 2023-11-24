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
import com.example.projetopdvmobile.adapter.ClienteListAdapter;
import com.example.projetopdvmobile.controller.ClienteController;
import com.example.projetopdvmobile.model.Cliente;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void salvarDados() {

        String retorno = controller.salvarCliente(edCPFCliente.getText().toString(),
                edNomeCliente.getText().toString(),
                edEmailCliente.getText().toString(),
                edTelefoneCliente.getText().toString());

        if (retorno != null) {

            if (retorno.contains("CPF")) {
                edCPFCliente.setError(retorno);
                edCPFCliente.requestFocus();
            }
            if (retorno.contains("NOME")) {
                edNomeCliente.setError(retorno);
                edNomeCliente.requestFocus();
            }
            if (retorno.contains("TELEFONE")) {
                edTelefoneCliente.setError(retorno);
                edTelefoneCliente.requestFocus();
            }
            if (retorno.contains("EMAIL")) {
                edEmailCliente.setError(retorno);
                edEmailCliente.requestFocus();
            }

        } else {
            Toast.makeText(this,
                    "Cliente salvo com sucesso!",
                    Toast.LENGTH_LONG).show();
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