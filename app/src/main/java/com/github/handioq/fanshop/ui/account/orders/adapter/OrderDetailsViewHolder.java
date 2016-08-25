package com.github.handioq.fanshop.ui.account.orders.adapter;

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
import com.github.handioq.fanshop.ui.cart.interaction.RemoveFromCartEvent;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.ui.productinfo.ProductActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cart_item_name)
    TextView itemNameView;

    @BindView(R.id.cart_item_price)
    TextView itemPriceView;

    @BindView(R.id.product_image)
    ImageView productImage;

    private ProductDVO product;

    static OrderDetailsViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_item, parent, false);
        return new OrderDetailsViewHolder(view);
    }

    private OrderDetailsViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product != null) {
                    Context context = itemView.getContext();
                    context.startActivity(ProductActivity.makeIntent(context, product.getId()));
                }
            }
        });
    }

    public void bind(ProductDVO item) {
        product = item;

        itemNameView.setText(item.getName());
        itemPriceView.setText(itemView.getContext().getString(R.string.catalog_price, item.getPrice()));
        itemPriceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(productImage);
    }
}