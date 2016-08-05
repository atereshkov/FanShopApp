package com.github.handioq.fanshop.cart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<ProductDTO> items;

    public CartRecyclerAdapter(List<ProductDTO> items) {
        this.items = items;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CartViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ProductDTO> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

}