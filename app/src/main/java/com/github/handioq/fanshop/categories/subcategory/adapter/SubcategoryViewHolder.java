package com.github.handioq.fanshop.categories.subcategory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.catalog.CatalogActivity;
import com.github.handioq.fanshop.categories.subcategory.SubcategoryActivity;
import com.github.handioq.fanshop.model.dto.SubcategoryDTO;
import com.github.handioq.fanshop.model.dvo.SubcategoryDVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubcategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.subcategory_name)
    TextView subcategoryNameView;

    @BindView(R.id.subcategory_image)
    ImageView subcategoryImageView;

    private SubcategoryDVO subcategory;

    static SubcategoryViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_item, parent, false);
        return new SubcategoryViewHolder(view);
    }

    private SubcategoryViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subcategory != null) {
                    Context context = itemView.getContext();
                    context.startActivity(CatalogActivity.makeIntent(context, subcategory.getName()));
                }
            }
        });
    }

    public void bind(final SubcategoryDVO item) {
        subcategory = item;

        subcategoryNameView.setText(item.getName());

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(subcategoryImageView);
    }
}