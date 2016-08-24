package com.github.handioq.fanshop.ui.productinfo;

import com.github.handioq.fanshop.model.dvo.ReviewDVO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class ReviewsPresenter implements ReviewsMvp.Presenter, ReviewsMvp.Model.Callback {

    private ReviewsMvp.View reviewsInfoView;
    private ReviewsMvp.Model reviewsInfoModel;

    private NetworkService networkService;

    private final static String TAG = "ReviewsPresenter";

    @Inject
    public ReviewsPresenter(NetworkService networkService) {
        reviewsInfoModel = new ReviewsModel(networkService);
        reviewsInfoModel.setCallback(this);
    }

    @Override
    public void onReviewsLoaded(List<ReviewDVO> reviews) {
        reviewsInfoView.hideProgress();
        reviewsInfoView.setReviews(reviews);
    }

    @Override
    public void onReviewsLoadError(Throwable error) {
        reviewsInfoView.onError(error);
        reviewsInfoView.hideProgress();
    }

    @Override
    public void getReviews(int id) {
        if (reviewsInfoView != null) {
           reviewsInfoView.showProgress();
        }

        reviewsInfoModel.getReviews(id);
    }

    @Override
    public void setView(ReviewsMvp.View view) {
        this.reviewsInfoView = view;
    }

    @Override
    public void onReviewsLoadCompleted() {
        reviewsInfoView.hideProgress();
    }
}
