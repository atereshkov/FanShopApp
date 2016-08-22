package com.github.handioq.fanshop.catalog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dbo.ProductDBO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogViewHolder> {

    private List<ProductDVO> items;

    public CatalogRecyclerAdapter(List<ProductDVO> items) {
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

    public void addItems(List<ProductDVO> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setItems(List<ProductDVO> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<ProductDVO> getItems() {
        return items;
    }
}