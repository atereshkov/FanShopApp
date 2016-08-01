package com.github.handioq.fanshop.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private ProductDTO mProductDTO;

    static CartViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);
        return new CartViewHolder(view);
    }

    private CartViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductDTO != null) {
                    Context context = itemView.getContext();
                    //Toast.makeText(context, "onItemClick " + buyButtonView.getTag().toString(), Toast.LENGTH_SHORT).show();

                    //context.startActivity(ProductInfoActivity.makeIntent(context, (int) buyButtonView.getTag()));
                }
            }
        });
    }

    public void bind(ProductDTO item) {
        mProductDTO = item;
    }
}