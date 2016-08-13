package com.github.handioq.fanshop.cart;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class RemoveFromCartModel implements RemoveFromCartMvp.Model {

    private final NetworkService networkService;
    private RemoveFromCartMvp.Model.Callback callback;

    public RemoveFromCartModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void removeProductFromCart(int userId, int productId) {
        networkService.getApiService()
                .removeProductFromCart(userId, productId)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onRemoveCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductRemoveError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProductRemoved();
                    }
                });
    }

    @Override
    public void setCallback(RemoveFromCartMvp.Model.Callback callback) {
        this.callback = callback;
    }

}
