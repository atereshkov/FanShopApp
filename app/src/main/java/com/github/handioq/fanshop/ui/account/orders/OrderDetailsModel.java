package com.github.handioq.fanshop.ui.account.orders;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import rx.Subscriber;

public class OrderDetailsModel implements OrderDetailsMvp.Model {

    private final NetworkService networkService;
    private OrderDetailsMvp.Model.Callback callback;

    private final static String TAG = "OrderDetailsModel";

    public OrderDetailsModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getOrderDetails(int userId, int orderId) {
        networkService.getApiService()
                .getOrderDetails(userId, orderId)
                .map(Mapper::mapOrdersDetailsToDvo)
                .compose(NetworkService.<OrderDetailsDVO>applyScheduler())
                .subscribe(new Subscriber<OrderDetailsDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDetailsLoadError(e);
                    }

                    @Override
                    public void onNext(OrderDetailsDVO order) {
                        callback.onDetailsLoaded(order);
                    }
                });

        Log.i(TAG, "getOrderDetails()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
