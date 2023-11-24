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

import com.example.projetopdvmobile.model.Item;

import java.util.ArrayList;

public class ItemListAdapter extends
        RecyclerView.Adapter<ItemListAdapter.ViewHolder> {


    private ArrayList<Item> listaItem;
    private Context context;

    /**
     * Construtor da classe
     *
     * @param listaItem: lista que será utilizado para retornar os dados a serem exibidos
     * @param context:   onde será exibido a lista
     */
    public ItemListAdapter(ArrayList<Item> listaItem, Context context) {
        this.listaItem = listaItem;
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
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_item,
                parent, false);

        return new ItemListAdapter.ViewHolder(listItem);
    }

    /**
     * Método que adiciona os dados de Aluno na tela
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ViewHolder holder, int position) {
        Item itemSelecionado = listaItem.get(position);

        holder.tvcodigo.setText(String.valueOf(itemSelecionado.getCod_produto()));
        holder.tvqtd_est.setText(String.valueOf(itemSelecionado.getQtd_est()));
        holder.tvDescricao.setText(itemSelecionado.getDescricao());

        // Convertendo os valores inteiros para String antes de definir o texto
        holder.tvVl_compra.setText(String.valueOf(itemSelecionado.getVl_compra()));
        holder.tvVl_venda.setText(String.valueOf(itemSelecionado.getVl_venda()));
    }


    /**
     * Retorna a quantidade de elementos contidos na lista
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return this.listaItem.size();
    }

    /**
     * Classe que vincula o componente do xml para ser manipulado
     **/
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvcodigo;
        public TextView tvqtd_est;
        public TextView tvDescricao;
        public TextView tvVl_compra;

        public TextView tvVl_venda;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvcodigo = itemView.findViewById(R.id.tvcodigo);
            this.tvqtd_est = itemView.findViewById(R.id.tvqtd_est);
            this.tvDescricao = itemView.findViewById(R.id.tvDescricao);
            this.tvVl_compra = itemView.findViewById(R.id.tvVl_compra);
            this.tvVl_venda = itemView.findViewById(R.id.tvVl_venda);

        }
    }

}

