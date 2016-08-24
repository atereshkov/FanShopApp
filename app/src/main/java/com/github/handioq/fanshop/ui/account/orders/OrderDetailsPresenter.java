package com.github.handioq.fanshop.ui.account.orders;

import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;

public class OrderDetailsPresenter implements OrderDetailsMvp.Presenter, OrderDetailsMvp.Model.Callback {


    @Override
    public void onDetailsLoaded(OrderDetailsDVO orderDetails) {

    }

    @Override
    public void onDetailsLoadError(Throwable error) {

    }

    @Override
    public void getOrderDetails(int orderId) {

    }

    @Override
    public void setView(OrderDetailsMvp.View view) {

    }
}
