package com.github.handioq.fanshop.ui.account.orders;

import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.net.NetworkService;

public class OrderDetailsPresenter implements OrderDetailsMvp.Presenter, OrderDetailsMvp.Model.Callback {

    private OrderDetailsMvp.View view;
    private OrderDetailsMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "OrderDetailsPresenter";

    public OrderDetailsPresenter(NetworkService networkService) {
        model = new OrderDetailsModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onDetailsLoaded(OrderDetailsDVO orderDetails) {
        view.setOrderDetails(orderDetails);
        view.hideProgress();
    }

    @Override
    public void onDetailsLoadError(Throwable error) {
        view.onError(error);
        view.hideProgress();
    }

    @Override
    public void onCompleted() {
        view.hideProgress();
    }

    @Override
    public void getOrderDetails(int userId, int orderId) {
        if (view != null) {
            view.showProgress();
        }

        model.getOrderDetails(userId, orderId);
    }

    @Override
    public void setView(OrderDetailsMvp.View view) {
        this.view = view;
    }
}
