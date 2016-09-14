package com.github.handioq.fanshop.ui.account.orders.iteraction;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class PayModel implements PayMvp.Model {

    private final NetworkService networkService;
    private Callback callback;

    public PayModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void paymentForOrder(int userId, int orderId) {
        networkService.getApiService()
                .paymentForOrder(userId, orderId)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onPayCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onPayError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onPaySuccess(response);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
