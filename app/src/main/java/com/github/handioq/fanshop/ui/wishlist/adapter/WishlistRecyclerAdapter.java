package com.github.handioq.fanshop.ui.wishlist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public class WishlistRecyclerAdapter extends RecyclerView.Adapter<WishlistViewHolder> {

    private List<ProductDVO> items;

    public WishlistRecyclerAdapter(List<ProductDVO> items) {
        this.items = items;
    }

    @Override
    public WishlistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WishlistViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final WishlistViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ProductDVO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<ProductDVO> getItems() {
        return items;
    }
}