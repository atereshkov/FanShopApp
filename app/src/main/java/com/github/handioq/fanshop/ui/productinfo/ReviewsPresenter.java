package com.github.handioq.fanshop.ui.productinfo;

import com.github.handioq.fanshop.model.dvo.ReviewListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class ReviewsPresenter implements ReviewsMvp.Presenter, ReviewsMvp.Model.Callback {

    private ReviewsMvp.View view;
    private ReviewsMvp.Model model;

    private NetworkService networkService;

    private final static String TAG = "ReviewsPresenter";

    @Inject
    public ReviewsPresenter(NetworkService networkService) {
        model = new ReviewsModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onReviewsLoaded(ReviewListDVO reviews) {
        if (view != null) {
            view.hideProgress();
            view.setReviews(reviews);
        }
    }

    @Override
    public void onReviewsLoadError(Throwable error) {
        if (view != null) {
            view.onError(error);
            view.hideProgress();
        }
    }

    @Override
    public void getReviews(int id) {
        if (view != null) {
           view.showProgress();
        }

        model.getReviews(id);
    }

    @Override
    public void setView(ReviewsMvp.View view) {
        this.view = view;
    }

    @Override
    public void onReviewsLoadCompleted() {
        if (view != null) {
            view.hideProgress();
        }
    }
}
