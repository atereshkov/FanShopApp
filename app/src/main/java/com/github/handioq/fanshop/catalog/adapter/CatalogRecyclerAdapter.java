package com.github.handioq.fanshop.catalog.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogRecyclerAdapter.ViewHolder>{

    private List<Product> items;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.tv_catalog_item)
        TextView mTextView;

        @Nullable @BindView(R.id.productImage)
        ImageView productImage;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public CatalogRecyclerAdapter(List<Product> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CatalogRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.mTextView != null) {
            holder.mTextView.setText(items.get(position).toString());
        }

        if (holder.productImage != null) {
            Glide.with(context).load(items.get(position).getImageUrl()).into(holder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
