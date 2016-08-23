package com.github.handioq.fanshop.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dvo.OrderDVO;

import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private List<OrderDVO> orders;

    public OrderRecyclerAdapter(List<OrderDVO> orders) {
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

    public void setOrders(List<OrderDVO> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }

}
