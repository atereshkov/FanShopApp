package com.github.handioq.fanshop.catalog.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.handioq.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.catalog_item_name)
    TextView catalogItemName;

    @Nullable
    @BindView(R.id.catalog_item_price)
    TextView catalogItemPrice;

    @Nullable
    @BindView(R.id.productImage)
    ImageView productImage;

    public CatalogViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}