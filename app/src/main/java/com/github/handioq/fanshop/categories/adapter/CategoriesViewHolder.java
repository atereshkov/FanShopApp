package com.github.handioq.fanshop.categories.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.handioq.R;
import com.github.handioq.fanshop.categories.subcategory.SubcategoryActivity;
import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.category_name)
    TextView categoryNameView;

    @BindView(R.id.category_image)
    ImageView categoryImageView;

    private CategoryDVO category;

    static CategoriesViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoriesViewHolder(view);
    }

    private CategoriesViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category != null) {
                    Context context = itemView.getContext();
                    context.startActivity(SubcategoryActivity.makeIntent(context, category.getId()));
                }
            }
        });
    }

    public void bind(final CategoryDVO item) {
        category = item;

        categoryNameView.setText(item.getName());

        Glide.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(categoryImageView);
    }
}