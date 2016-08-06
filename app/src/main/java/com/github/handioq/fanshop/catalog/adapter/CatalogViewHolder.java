package com.github.handioq.fanshop.catalog.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.catalog.CatalogView;
import com.github.handioq.fanshop.catalog.ViewEvent;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.productinfo.ProductInfoActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

class CatalogViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.catalog_item_name)
    TextView catalogItemNameView;

    @BindView(R.id.catalog_item_price)
    TextView catalogItemPriceView;

    @BindView(R.id.product_image)
    ImageView productImage;

    @BindView(R.id.buy_button)
    ImageButton buyButtonView;

    private ProductDTO productDTO;
    private CatalogView catalogView;

    static CatalogViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);
        return new CatalogViewHolder(view);
    }

    private CatalogViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productDTO != null) {
                    Context context = itemView.getContext();
                    Toast.makeText(context,
                            "onItemClick " + productDTO.getId(), Toast.LENGTH_SHORT).show();

                    //context.startActivity(ProductInfoActivity.makeIntent(context, (int) buyButtonView.getTag()));
                    context.startActivity(ProductInfoActivity.makeIntent(context, productDTO.getId()));
                }
            }
        });
    }

    public void bind(final ProductDTO item) {
        productDTO = item;
        catalogItemNameView.setText(item.getName());

        buyButtonView.setTag(getAdapterPosition());
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(itemView.getContext(), "Click buy button on product " + productDTO, Toast.LENGTH_SHORT).show();

                if (catalogView != null)
                    catalogView.onAddToCartClicked(productDTO);
            }
        });

        catalogItemPriceView.setText(itemView.getContext().getString(R.string.catalog_price, item.getPrice()));
        catalogItemPriceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(productImage);
    }

    @Subscribe(sticky = true)
    public void onViewEvent(ViewEvent event) {
        this.catalogView = event.catalogView;
    }
}