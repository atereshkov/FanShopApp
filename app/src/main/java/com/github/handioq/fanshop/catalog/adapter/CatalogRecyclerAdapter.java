package com.github.handioq.fanshop.catalog.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.handioq.R;
import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogRecyclerAdapter.ViewHolder>{

    private List<Product> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_catalog_item);
        }
    }

    public CatalogRecyclerAdapter(List<Product> items) {
        this.items = items;
    }

    @Override
    public CatalogRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(items.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
