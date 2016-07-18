package com.github.handioq.fanshop.catalog.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

public class CatalogRecyclerAdapter extends RecyclerView.Adapter<CatalogViewHolder> {

    private List<Product> items;
    private Context context;

    public CatalogRecyclerAdapter(List<Product> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);

        return new CatalogViewHolder(v);
    }



    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        if (holder.catalogItemName != null) {
            holder.catalogItemName.setText(items.get(position).getName());
        }

        if (holder.catalogItemPrice != null) {
            Resources res = context.getResources();

            String itemPrice = String.format(res.getString(R.string.catalog_price), items.get(position).getPrice());
            holder.catalogItemPrice.setText(itemPrice);
            holder.catalogItemPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
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
