package com.github.handioq.fanshop.ui.productinfo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.handioq.fanshop.model.dvo.ReviewDVO;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private final List<ReviewDVO> items;

    public ReviewAdapter(List<ReviewDVO> items) {
        this.items = items;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(final ReviewViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
