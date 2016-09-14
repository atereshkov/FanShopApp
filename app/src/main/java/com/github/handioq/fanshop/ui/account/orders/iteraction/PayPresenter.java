package com.github.handioq.fanshop.ui.account.orders.iteraction;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import javax.inject.Inject;

public class PayPresenter implements PayMvp.Presenter, PayMvp.Model.Callback {

    private PayMvp.View view;
    private PayModel model;
    private NetworkService networkService;

    @Inject
    public PayPresenter(NetworkService networkService) {
        this.networkService = networkService;

        model = new PayModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void paymentForOrder(int userId, int orderId) {
        model.paymentForOrder(userId, orderId);
    }

    @Override
    public void setView(PayMvp.View addToCartView) {
        this.view = addToCartView;
    }

    @Override
    public void onPaySuccess(Response response) {
        view.onPaySuccess(response);
    }

    @Override
    public void onPayError(Throwable error) {
        view.onPayError(error);
    }

    @Override
    public void onPayCompleted() {

    }
}
