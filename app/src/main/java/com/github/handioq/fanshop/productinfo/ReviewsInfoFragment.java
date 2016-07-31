package com.github.handioq.fanshop.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.productinfo.adapter.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReviewsInfoFragment extends BaseFragment {

    @BindView(R.id.reviews_recycler_view)
    RecyclerView recyclerView;

    private ReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int selectedItem;

    private final static String TAG = "ReviewsInfoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_info_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBundle(getArguments());

        List<ReviewDTO> list = new ArrayList<>(); // TODO THIS IS JUST FOR TEST, DELETE!
        list.add(new ReviewDTO("message 1", 2));
        list.add(new ReviewDTO("some awsome review about this fckni humburger doto awsm review multiline text lipsum liospz multiline text a123 as123", 2));
        list.add(new ReviewDTO("nice go asdhome dt2", 3));

        initRecyclerView(list);
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            selectedItem = bundle.getInt("id");
        }
    }

    private void initRecyclerView(List<ReviewDTO> reviews)
    {
        Log.e(TAG, "initRecyclerView()");

        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(reviewAdapter);
    }
}
