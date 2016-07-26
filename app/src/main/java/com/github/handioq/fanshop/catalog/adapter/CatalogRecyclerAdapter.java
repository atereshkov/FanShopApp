package com.github.handioq.fanshop.catalog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.Product;

import java.util.List;

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogViewHolder> {

    private final List<Product> items;

    public CatalogRecyclerAdapter(List<Product> items) {
        this.items = items;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CatalogViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final CatalogViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
