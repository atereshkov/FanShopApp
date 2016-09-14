package com.github.handioq.fanshop.ui.productinfo.reviews.iteraction;

import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class AddReviewModel implements AddReviewMvp.Model {

    private final NetworkService networkService;
    private Callback callback;

    public AddReviewModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addReview(int userId, int productId, ReviewDTO review) {
        networkService.getApiService()
                .addReview(userId, productId, review)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onReviewCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onReviewAddError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onReviewAdded(response);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
