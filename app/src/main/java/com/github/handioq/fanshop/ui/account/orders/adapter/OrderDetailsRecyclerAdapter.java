package com.github.handioq.fanshop.ui.account.orders.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public class OrderDetailsRecyclerAdapter extends RecyclerView.Adapter<OrderDetailsViewHolder> {

    private List<ProductDVO> products;

    public OrderDetailsRecyclerAdapter(List<ProductDVO> products) {
        this.products = products;
    }

    @Override
    public OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OrderDetailsViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final OrderDetailsViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOrders(List<ProductDVO> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }

}