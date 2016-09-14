package com.github.handioq.fanshop.ui.productinfo.reviews.iteraction;

import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import javax.inject.Inject;

public class AddReviewPresenter implements AddReviewMvp.Presenter, AddReviewMvp.Model.Callback {

    private AddReviewMvp.View view;
    private AddReviewModel model;
    private NetworkService networkService;

    @Inject
    public AddReviewPresenter(NetworkService networkService) {
        this.networkService = networkService;

        model = new AddReviewModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void addReview(int userId, int productId, ReviewDTO review) {
        model.addReview(userId, productId, review);
    }

    @Override
    public void setView(AddReviewMvp.View addToCartView) {
        this.view = addToCartView;
    }

    @Override
    public void onReviewAdded(Response response) {
        view.onReviewAddSuccess(response);
    }

    @Override
    public void onReviewAddError(Throwable error) {
        view.onReviewAddError(error);
    }

    @Override
    public void onReviewCompleted() {

    }
}
