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

public class ReviewsInfoFragment extends BaseFragment implements ReviewsInfoMvp.View {

    @BindView(R.id.reviews_recycler_view)
    RecyclerView recyclerView;

    private ReviewAdapter reviewAdapter;
    private int productId;

    private final static String TAG = "ReviewsInfoFragment";

    @Inject
    ReviewsInfoMvp.Presenter reviewsPresenter;

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

/*        List<ReviewDTO> list = new ArrayList<>(); // TODO THIS IS JUST FOR TEST, DELETE!
        list.add(new ReviewDTO("message 1", 2));
        list.add(new ReviewDTO("some awsome review about this fckni humburger doto awsm review multiline text lipsum liospz multiline text a123 as123", 2));
        list.add(new ReviewDTO("nice go asdhome dt2", 3));

        initRecyclerView(list);*/
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            productId = bundle.getInt("id");
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
