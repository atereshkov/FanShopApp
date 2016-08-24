package com.github.handioq.fanshop.ui.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ReviewDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class ReviewsModel implements ReviewsMvp.Model {

    private final NetworkService networkService;
    private ReviewsModel.Callback callback;

    private final static String TAG = "ReviewsModel";

    public ReviewsModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getReviews(int id) {

        networkService.getApiService()
                .getReviews(id)
                .map(Mapper::mapReviewsToDvo)
                .compose(NetworkService.<List<ReviewDVO>>applyScheduler())
                .subscribe(new Subscriber<List<ReviewDVO>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onReviewsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ReviewDVO> reviews) {
                        callback.onReviewsLoaded(reviews);
                    }
                });

        Log.i(TAG, "getReviews()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
