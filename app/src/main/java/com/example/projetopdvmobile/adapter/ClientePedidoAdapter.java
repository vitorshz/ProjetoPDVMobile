package com.example.projetopdvmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.model.Cliente;
import com.example.projetopdvmobile.model.Pedido;

import java.util.ArrayList;

public class ClientePedidoAdapter extends
        RecyclerView.Adapter<ClientePedidoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cliente> listaClientes; // Adicionado para armazenar a lista de clientes
    private OnAddClientePedidoClickListener addClientePedidoClickListener;

    public interface OnAddClientePedidoClickListener {
        void onAddClientePedidoClick(int position);
    }

    public ClientePedidoAdapter(ArrayList<Cliente> listaClientes, OnAddClientePedidoClickListener listener) {
        this.listaClientes = listaClientes;
        this.addClientePedidoClickListener = listener;
    }

    // Adicione este método para atualizar a lista de clientes
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_clientes,
                parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);

        holder.tvNome.setText(cliente.getNome());
        holder.tvCpf.setText(cliente.getCpf());
        holder.tvTelefone.setText(cliente.getTelefone());

        // Configurar o clique do botão
        holder.btAddClientePedido.setVisibility(View.VISIBLE);
        holder.btAddClientePedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar se o ouvinte está definido
                if (addClientePedidoClickListener != null) {
                    // Chamar o método de clique do botão na interface
                    addClientePedidoClickListener.onAddClientePedidoClick(position);
                }
            }
        });


    }



    @Override
    public int getItemCount() {
        // Verifica se a listaClientes é nula antes de retornar o tamanho
        return listaClientes != null ? listaClientes.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCpf;
        public TextView tvNome;
        public TextView tvEmail;
        public TextView tvTelefone;
        public ImageButton btAddClientePedido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCpf = itemView.findViewById(R.id.tvCpf);
            this.tvNome = itemView.findViewById(R.id.tvNome);
            this.tvEmail = itemView.findViewById(R.id.tvEmail);
            this.tvTelefone = itemView.findViewById(R.id.tvTelefone);
            this.btAddClientePedido = itemView.findViewById(R.id.btAddClientePedido);
        }
    }
}


