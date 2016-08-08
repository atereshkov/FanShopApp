package com.github.handioq.fanshop.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.OrderDTO;

import java.util.List;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountViewHolder> {

    private List<OrderDTO> orders;

    public AccountRecyclerAdapter(List<OrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AccountViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final AccountViewHolder holder, int position) {
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
