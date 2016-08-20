package com.github.handioq.fanshop.ui.wishlist.adapter;

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
import com.github.handioq.fanshop.catalog.AddToCartClickEvent;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.productinfo.ProductActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishlistViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.catalog_item_name)
    TextView catalogItemNameView;

    @BindView(R.id.catalog_item_price)
    TextView catalogItemPriceView;

    @BindView(R.id.product_image)
    ImageView productImage;

    @BindView(R.id.buy_button)
    ImageButton buyButtonView;

    private ProductDTO product;

    static WishlistViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);
        return new WishlistViewHolder(view);
    }

    private WishlistViewHolder(View v) {
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

    public void bind(final ProductDTO item) {
        product = item;
        catalogItemNameView.setText(item.getName());

        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EventBus.getDefault().post(new AddToCartClickEvent(product));
            }
        });

        catalogItemPriceView.setText(itemView.getContext().getString(R.string.catalog_price, item.getPrice()));
        catalogItemPriceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(productImage);
    }
}