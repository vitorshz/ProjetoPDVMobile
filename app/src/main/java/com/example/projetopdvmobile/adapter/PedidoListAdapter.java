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
import com.example.projetopdvmobile.model.Pedido;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.ViewHolder> {

    private ArrayList<Pedido> listaPedidos;
    private Context context;

    public PedidoListAdapter(ArrayList<Pedido> listaPedidos, Context context) {
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pedido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);

        holder.tvNumPedido.setText(String.valueOf(pedido.getNumPedido()));
        holder.tvVlTotal.setText(new DecimalFormat("R$ #,##0.00").format(pedido.getVlTotal()));
        holder.tvCliente.setText(pedido.getCliente().getNome());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        holder.tvDataPedido.setText(dateFormat.format(pedido.getDataCriacao()));

        // Se necessário, ajuste para exibir os produtos do pedido
        // holder.tvProdutosPedido.setText("Produtos: ...");

        // Implemente a lógica para os botões de edição e exclusão se necessário
        // holder.btEdit.setOnClickListener(...);
        // holder.btExcluir.setOnClickListener(...);
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDataPedido;
        public TextView tvNumPedido;
        public TextView tvVlTotal;
        public TextView tvCliente;
        public TextView tvProdutosPedido;
        public ImageButton btEdit;
        public ImageButton btExcluir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumPedido = itemView.findViewById(R.id.tvNumPedido);
            tvVlTotal = itemView.findViewById(R.id.tvVlTotal);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvProdutosPedido = itemView.findViewById(R.id.tvProdutosPedido);
            btEdit = itemView.findViewById(R.id.btEdit);
            btExcluir = itemView.findViewById(R.id.btExcluir);
        }
    }
}
