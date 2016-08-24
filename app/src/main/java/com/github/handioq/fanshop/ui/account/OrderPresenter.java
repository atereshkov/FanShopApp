package com.github.handioq.fanshop.ui.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.OrderDVO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class OrderPresenter implements OrderMvp.Presenter, OrderMvp.Model.Callback {

    private OrderMvp.View accountView;
    private OrderMvp.Model accountModel;
    private NetworkService networkService;

    private final static String TAG = "OrderPresenter";

    @Inject
    public OrderPresenter(NetworkService networkService) {
        accountModel = new OrderModel(networkService);
        accountModel.setCallback(this);
    }

    @Override
    public void getOrders(int userId) {
        if (accountView != null) {
            accountView.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on orderView");
        }

        accountModel.gerOrders(userId);
    }

    @Override
    public void setView(OrderMvp.View view) {
        this.accountView = view;
    }

    @Override
    public void onOrdersLoaded(List<OrderDVO> orders) {
        accountView.setOrders(orders);
    }

    @Override
    public void onOrdersLoadError(Throwable error) {
        accountView.onError(error);
    }

    @Override
    public void onCompleted() {
        accountView.hideProgress();
    }
}
