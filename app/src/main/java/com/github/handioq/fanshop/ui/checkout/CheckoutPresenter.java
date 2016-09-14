package com.github.handioq.fanshop.ui.checkout;

import com.github.handioq.fanshop.model.dto.ProductIdDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import java.util.List;

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
        if (view != null) {
            view.hideProgress();
            view.setResponse(response);
        }
    }

    @Override
    public void onOrderCreateError(Throwable error) {
        if (view != null) {
            view.hideProgress();
            view.onError(error);
        }
    }

    @Override
    public void onOrderCompleted() {
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void createOrder(int userId, List<ProductIdDTO> products) {
        if (view != null) {
            view.showProgress();
        }

        model.createOrder(userId, products);
    }

    @Override
    public void setView(CheckoutMvp.View view) {
        this.view = view;
    }
}
