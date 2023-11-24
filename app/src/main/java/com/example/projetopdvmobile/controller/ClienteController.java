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

    public String salvarCliente(int id,String cpf, String nome, String email, String telefone){
        try{
            if(id==0 || id==(id-1)){
                return "Informe o ID do Cliente!";
            }
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

            Cliente cliente = ClienteDAO.getInstancia(context)
                    .getById(Integer.parseInt(cpf));

            if(cliente != null){
                return "O cpf ("+cpf+") já está cadastrado!";
            }else{


                cliente = new Cliente();
                cliente.setId(id);
                cliente.setCpf(cpf);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);

                ClienteDAO.getInstancia(context).insert(cliente);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Cliente.";
        }
        return null;
    }

    /**
     * Retorna todos os alunos cadastrados no banco
     * @return
     */
    public ArrayList<Cliente> retornarTodosClientes(){
        return ClienteDAO.getInstancia(context).getAll();

    }
}
