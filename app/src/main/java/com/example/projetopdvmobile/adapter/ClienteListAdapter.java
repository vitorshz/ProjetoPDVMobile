package com.example.projetopdvmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.model.Cliente;

import java.util.ArrayList;

public class ClienteListAdapter extends
        RecyclerView.Adapter<ClienteListAdapter.ViewHolder> {

    private ArrayList<Cliente> listaClientes;
    private Context context;


    /**
     * Construtor da classe
     *
     * @param listaClientes: lista que será utilizado para retornar os dados a serem exibidos
     * @param context:     onde será exibido a lista
     */
    public ClienteListAdapter(ArrayList<Cliente> listaClientes, Context context) {
        this.listaClientes = listaClientes;
        this.context = context;
    }

    /**
     * Método responsável em carregar o arquivo de layout na tela
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return o arquivo xml com seus componentes
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_clientes,
                parent, false);

        return new ViewHolder(listItem);
    }

    /**
     * Método que adiciona os dados de Aluno na tela
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cliente clienteSelecionado = listaClientes.get(position);

        holder.tvCpf.setText(String.valueOf(clienteSelecionado.getCpf()));
        holder.tvNome.setText(clienteSelecionado.getNome());
        holder.tvTelefone.setText(clienteSelecionado.getTelefone());
        holder.tvEmail.setText(clienteSelecionado.getEmail());

    }

    /**
     * Retorna a quantidade de elementos contidos na lista
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return this.listaClientes.size();
    }

    /**
     * Classe que vincula o componente do xml para ser manipulado
     **/
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCpf;
        public TextView tvNome;
        public TextView tvEmail;
        public TextView tvTelefone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvCpf = itemView.findViewById(R.id.tvCpf);
            this.tvNome = itemView.findViewById(R.id.tvNome);
            this.tvEmail = itemView.findViewById(R.id.tvEmail);
            this.tvTelefone = itemView.findViewById(R.id.tvTelefone);

        }
    }


}
