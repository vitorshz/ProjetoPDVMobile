package com.example.projetopdvmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.model.Item;
import com.example.projetopdvmobile.model.ItemVenda;

import java.util.ArrayList;

public class ItemVendaListAdapter extends RecyclerView.Adapter<ItemVendaListAdapter.ViewHolder> {

    private ArrayList<ItemVenda> listaItemVenda;

    private ArrayList<Item> listaItens;
    private Context context;

    public ItemVendaListAdapter(ArrayList<ItemVenda> listaItemVenda, ArrayList<Item> listaItens, Context context) {
        this.listaItemVenda = listaItemVenda;
        this.listaItens = listaItens;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemVendaListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_item_venda, parent, false);
        return new ItemVendaListAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVendaListAdapter.ViewHolder holder, int position) {
        ItemVenda itemVendaSelecionado = listaItemVenda.get(position);
        Item item = listaItens.get(position);

        holder.tvNomeProduto.setText(item.getDescricao());
        holder.tvQuantidade.setText(String.valueOf(itemVendaSelecionado.getQuantidade()));
        holder.tvSubtotal.setText(String.valueOf(itemVendaSelecionado.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return this.listaItemVenda.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNomeProduto;
        public TextView tvQuantidade;
        public TextView tvSubtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvNomeProduto = itemView.findViewById(R.id.tvNomeProduto);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        }
    }
}




