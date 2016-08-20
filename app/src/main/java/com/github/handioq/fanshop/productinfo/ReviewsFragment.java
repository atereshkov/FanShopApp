package com.github.handioq.fanshop.productinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.productinfo.adapter.ReviewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ReviewsFragment extends BaseFragment implements ReviewsMvp.View {

    @BindView(R.id.reviews_recycler_view)
    RecyclerView recyclerView;

    private ReviewAdapter reviewAdapter;
    private int productId;

    private final static String TAG = "ReviewsFragment";
    private final static String ARGUMENT_ID = "id";

    @Inject
    ReviewsMvp.Presenter reviewsPresenter;

    public static ReviewsFragment newInstance(int id) {
        ReviewsFragment fragment = new ReviewsFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        ((FanShopApp) getContext().getApplicationContext()).getProductInfoComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_info_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBundle(getArguments());

        reviewsPresenter.setView(this);
        reviewsPresenter.getReviews(productId);
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            productId = bundle.getInt(ARGUMENT_ID);
        }
    }

    private void initRecyclerView(List<ReviewDTO> reviews)
    {
        Log.i(TAG, "initRecyclerView()");

        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(reviewAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setReviews(List<ReviewDTO> reviews) {
        initRecyclerView(reviews);
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
