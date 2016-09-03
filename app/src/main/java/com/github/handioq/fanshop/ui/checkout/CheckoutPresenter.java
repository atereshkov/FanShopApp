package com.github.handioq.fanshop.ui.checkout;

import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import javax.inject.Inject;

public class CheckoutPresenter implements CheckoutMvp.Presenter, CheckoutMvp.Model.Callback {

    private CheckoutMvp.View view;
    private CheckoutMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "CheckoutPresenter";

    @Inject
    public CheckoutPresenter(NetworkService networkService) {
        model = new CheckoutModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onOrderCreated(Response response) {
        view.hideProgress();
        view.setResponse(response);
    }

    @Override
    public void onOrderCreateError(Throwable error) {
        view.hideProgress();
        view.onError(error);
    }

    @Override
    public void onOrderCompleted() {
        view.hideProgress();
    }

    @Override
    public void createOrder(int userId, OrderDTO order) {
        if (view != null) {
            view.showProgress();
        }

        model.createOrder(userId, order);
    }

    @Override
    public void setView(CheckoutMvp.View view) {
        this.view = view;
    }
}
