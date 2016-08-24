package com.github.handioq.fanshop.ui.catalog.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<ProductDVO> items;

    public SearchRecyclerAdapter(List<ProductDVO> items) {
        this.items = items;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {
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

    public void clearItems() {
        this.items.clear();
        notifyDataSetChanged();
    }
}