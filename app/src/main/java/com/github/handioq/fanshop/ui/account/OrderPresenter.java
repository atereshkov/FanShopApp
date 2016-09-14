package com.github.handioq.fanshop.ui.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.OrderListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class OrderPresenter implements OrderMvp.Presenter, OrderMvp.Model.Callback {

    private OrderMvp.View view;
    private OrderMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "OrderPresenter";

    @Inject
    public OrderPresenter(NetworkService networkService) {
        model = new OrderModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void getOrders(int userId) {
        if (view != null) {
            view.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on orderView");
        }

        model.getOrders(userId);
    }

    @Override
    public void setView(OrderMvp.View view) {
        this.view = view;
    }

    @Override
    public void onOrdersLoaded(OrderListDVO orders) {
        if (view != null) {
            view.setOrders(orders);
        }
    }

    @Override
    public void onOrdersLoadError(Throwable error) {
        if (view != null) {
            view.onError(error);
        }
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.hideProgress();
        }
    }
}
