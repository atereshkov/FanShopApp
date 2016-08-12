package com.github.handioq.fanshop.categories.subcategory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dto.SubcategoryDTO;

import java.util.List;

public class SubcategoryRecyclerAdapter extends RecyclerView.Adapter<SubcategoryViewHolder> {

    private List<SubcategoryDTO> subcategories;

    public SubcategoryRecyclerAdapter(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public SubcategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SubcategoryViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final SubcategoryViewHolder holder, int position) {
        holder.bind(subcategories.get(position));
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    public List<SubcategoryDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDTO> subcategories) {
        this.subcategories = subcategories;
    }
}