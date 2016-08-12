package com.github.handioq.fanshop.categories.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.CategoryDTO;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private List<CategoryDTO> categories;

    public CategoriesRecyclerAdapter(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CategoriesViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final CategoriesViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}