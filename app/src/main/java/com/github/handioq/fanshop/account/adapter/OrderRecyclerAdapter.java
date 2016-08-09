package com.github.handioq.fanshop.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.OrderDTO;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<OrderDTO> orders;

    public OrderRecyclerAdapter(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return OrderViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final OrderViewHolder holder, int position) {
        holder.bind(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<OrderDTO> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }

}
