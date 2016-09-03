package com.github.handioq.fanshop.ui.categories.subcategory.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dvo.CategoryDVO;

import java.util.List;

public class SubcategoryRecyclerAdapter extends RecyclerView.Adapter<SubcategoryViewHolder> {

    private List<CategoryDVO> categories;

    public SubcategoryRecyclerAdapter(List<CategoryDVO> subcategories) {
        this.categories = subcategories;
    }

    @Override
    public SubcategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SubcategoryViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final SubcategoryViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public List<CategoryDVO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDVO> categories) {
        this.categories = categories;
    }
}