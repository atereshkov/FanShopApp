package com.github.handioq.fanshop.cart.interaction;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class AddToCartModel implements AddToCartMvp.Model {

    private final NetworkService networkService;
    private AddToCartModel.Callback callback;

    public AddToCartModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addProductToCart(int userId, int productId) {
        networkService.getApiService()
                .addProductToCart(userId, productId)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onAddToCartCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductAddError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProductAdded(response);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
