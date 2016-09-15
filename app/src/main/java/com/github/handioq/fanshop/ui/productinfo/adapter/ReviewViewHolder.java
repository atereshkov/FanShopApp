package com.github.handioq.fanshop.ui.productinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.model.dvo.ReviewDVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.review_message)
    TextView catalogItemNameView;

    @BindView(R.id.rating_bar_small)
    RatingBar ratingBar;

    @BindView(R.id.author)
    TextView authorView;

    private ReviewDVO review;

    static ReviewViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    private ReviewViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (review != null) {
                    Context context = itemView.getContext();
                    //Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void bind(ReviewDVO item) {
        review = item;
        catalogItemNameView.setText(item.getMessage());
        ratingBar.setRating(item.getStars());
        authorView.setText(item.getAuthor());
    }
}