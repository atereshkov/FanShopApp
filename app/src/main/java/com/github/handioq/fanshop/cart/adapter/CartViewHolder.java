package com.github.handioq.fanshop.cart.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.cart.RemoveFromCartEvent;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.productinfo.ProductActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.remove_button)
    ImageButton removeButton;

    @BindView(R.id.cart_item_name)
    TextView cartItemNameView;

    @BindView(R.id.cart_item_price)
    TextView cartItemPriceView;

    @BindView(R.id.product_image)
    ImageView productImage;

    private ProductDTO productDTO;

    static CartViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    private CartViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productDTO != null) {
                    Context context = itemView.getContext();
                    context.startActivity(ProductActivity.makeIntent(context, productDTO.getId()));
                }
            }
        });
    }

    public void bind(ProductDTO item) {
        productDTO = item;

        cartItemNameView.setText(item.getName());

        removeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new RemoveFromCartEvent(productDTO.getId()));
            }
        });

        cartItemPriceView.setText(itemView.getContext().getString(R.string.catalog_price, item.getPrice()));
        cartItemPriceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(productImage);
    }
}