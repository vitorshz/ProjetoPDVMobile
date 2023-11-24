package com.example.projetopdvmobile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetopdvmobile.View.CadastroAdmActivity;
import com.example.projetopdvmobile.View.CadastroClienteActivity;
import com.example.projetopdvmobile.View.CadastroItemActivity;
import com.example.projetopdvmobile.View.CadastroPedidoActivity;
import com.example.projetopdvmobile.View.GerenciadorPedidosActivity;

public class MainActivity extends AppCompatActivity {
    private Button btCadastroCliente;
    private Button btCadastroAdm;
    private Button btCadastroItem;
    private Button btCadastroPedido;
    private Button btPesquisarPedido;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCadastroCliente = findViewById(R.id.btCadastroCliente);
        btCadastroAdm = findViewById(R.id.btCadastroAdm);
        btCadastroItem = findViewById(R.id.btCadastroItem);
        btCadastroPedido = findViewById(R.id.btCadastroPedido);
        btPesquisarPedido = findViewById(R.id.btPesquisarPedido);
        btCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        abrirActivity(CadastroAdmActivity.class);
            }
        });
        btCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                abrirActivity(CadastroClienteActivity.class); } });
        btCadastroItem.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                abrirActivity(CadastroItemActivity.class);
            } });
        btCadastroPedido.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                abrirActivity(CadastroPedidoActivity.class); } });
        btPesquisarPedido.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                abrirActivity(GerenciadorPedidosActivity.class);
            } }); }
    private void abrirActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    } }