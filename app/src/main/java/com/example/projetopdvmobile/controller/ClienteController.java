package com.example.projetopdvmobile.controller;

import android.content.Context;

import com.example.projetopdvmobile.dao.ClienteDAO;
import com.example.projetopdvmobile.model.Cliente;

import java.util.ArrayList;

public class ClienteController {

    private Context context;

    public ClienteController(Context context) {
        this.context = context;
    }

    public String salvarCliente(String cpf, String nome, String email, String telefone){
        try{

            if(cpf.equals("") || cpf.isEmpty()){
                return "Informe o Cpf do Cliente!";
            }
            if(nome.equals("") || nome.isEmpty()){
                return "Informe o NOME do cliente!";
            }
            if(email.equals("") || email.isEmpty()){
                return "Informe o ema do Cliente!";
            }
            if(telefone.equals("") || telefone.isEmpty()){
                return "Informe o tel do cliente!";
            }

            // Verificar se o CPF já está cadastrado
            Cliente clienteExistente = ClienteDAO.getInstancia(context).getByCpf(cpf);

            if (clienteExistente != null) {
                return "O CPF (" + cpf + ") já está cadastrado!";
            } else {
                // Cliente não existe, pode ser inserido
                Cliente novoCliente = new Cliente();
                novoCliente.setCpf(cpf);
                novoCliente.setNome(nome);
                novoCliente.setEmail(email);
                novoCliente.setTelefone(telefone);

                ClienteDAO.getInstancia(context).insert(novoCliente);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Cliente.";
        }
        return null;
    }


    public ArrayList<Cliente> retornarTodosClientes(){
        return ClienteDAO.getInstancia(context).getAll();

    }
}
