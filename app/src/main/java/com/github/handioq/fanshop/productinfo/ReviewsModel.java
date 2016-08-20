package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.net.NetworkService;

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
                .compose(NetworkService.<List<ReviewDTO>>applyScheduler())
                .subscribe(new Subscriber<List<ReviewDTO>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onReviewsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ReviewDTO> reviews) {
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
