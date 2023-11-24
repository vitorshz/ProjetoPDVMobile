package com.example.projetopdvmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdvmobile.R;
import com.example.projetopdvmobile.model.Item;
import com.example.projetopdvmobile.model.Pedido;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.PedidoViewHolder> {

    private List<Pedido> listaPedidos;
    private OnExcluirPedidoClickListener excluirPedidoClickListener;

    public PedidoListAdapter(List<Pedido> listaPedidos, OnExcluirPedidoClickListener listener) {
        this.listaPedidos = listaPedidos;
        this.excluirPedidoClickListener = listener;
    }
    public interface OnExcluirPedidoClickListener {
        void onExcluirPedidoClick(int position, int itemIndex);
    }
    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pedido, parent, false);
        return new PedidoViewHolder(itemView);
    }

    @Override

    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);

        holder.tvNumPedido.setText(String.valueOf(pedido.getNumPedido()));

        // Configurar o valor total do pedido
        holder.tvVlTotal.setText(new DecimalFormat("R$ #,##0.00").format(pedido.getVlTotal()));


        if (pedido.getListaProdutos() != null && !pedido.getListaProdutos().isEmpty()) {
            // Por exemplo, pega o primeiro item da lista de produtos
            Item primeiroItem = pedido.getListaProdutos().get(0);

            // Verifica se o item não é nulo
            if (primeiroItem != null) {
                // Agora você pode acessar o valor de venda do item
                double valorVenda = primeiroItem.getVl_venda();

                // Faça algo com o valor de venda, por exemplo, exiba em um TextView
                holder.tvVlTotal.setText(String.valueOf(valorVenda));
            }
        }

        // Configurar a lista de produtos do pedido
        StringBuilder produtosText = new StringBuilder("Produtos: ");
        for (Item produto : pedido.getListaProdutos()) {
            produtosText.append(produto.getDescricao()).append(", ");
            produtosText.append(produto.getVl_venda()).append(", ");
        }

        // Remover a última vírgula e espaço
        if (produtosText.length() > 2) {
            produtosText.setLength(produtosText.length() - 2);
        }
        holder.tvProdutosPedido.setText(produtosText.toString());

    }


    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class PedidoViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNumPedido;
        public TextView tvVlTotal;
        public TextView tvCliente;
        public TextView tvProdutosPedido;
        public TextView tvDataPedido;
        public ImageButton btExcluir;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumPedido = itemView.findViewById(R.id.tvNumPedido);
            tvVlTotal = itemView.findViewById(R.id.tvVlTotal);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvProdutosPedido = itemView.findViewById(R.id.tvProdutosPedido);
            tvDataPedido = itemView.findViewById(R.id.tvDataPedido);
            btExcluir = itemView.findViewById(R.id.btExcluir);
        }
    }
}
