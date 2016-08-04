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
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.productinfo.ProductInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

class CatalogViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.catalog_item_name)
    TextView catalogItemNameView;

    @BindView(R.id.catalog_item_price)
    TextView catalogItemPriceView;

    @BindView(R.id.productImage)
    ImageView productImage;

    @BindView(R.id.buy_button)
    ImageButton buyButtonView;

    private ProductDTO mProductDTO;

    static CatalogViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false);
        return new CatalogViewHolder(view);
    }

    private CatalogViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductDTO != null) {
                    Context context = itemView.getContext();
                    Toast.makeText(context,
                            "onItemClick " + mProductDTO.getId(), Toast.LENGTH_SHORT).show();

                    //context.startActivity(ProductInfoActivity.makeIntent(context, (int) buyButtonView.getTag()));
                    context.startActivity(ProductInfoActivity.makeIntent(context, mProductDTO.getId()));
                }
            }
        });
    }

    public void bind(ProductDTO item) {
        mProductDTO = item;
        catalogItemNameView.setText(item.getName());

        buyButtonView.setTag(getAdapterPosition());
        buyButtonView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),
                        "Click buy button on product " + mProductDTO, Toast.LENGTH_SHORT).show();
            }
        });

        catalogItemPriceView.setText(itemView.getContext().getString(R.string.catalog_price, item.getPrice()));
        catalogItemPriceView.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.colorAccent));

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(productImage);
    }
}