package com.github.handioq.fanshop.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Subscriber;

public class OrderModel implements OrderMvp.Model {

    private NetworkService networkService;
    private OrderMvp.Model.Callback callback;

    private final static String TAG = "OrderModel";

    public OrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void gerOrders(int userId) {
        networkService.getApiService()
                .getOrders(userId)
                .compose(NetworkService.<List<OrderDTO>>applyScheduler())
                .subscribe(new Subscriber<List<OrderDTO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrdersLoadError(e);
                    }

                    @Override
                    public void onNext(List<OrderDTO> order) {
                        callback.onOrdersLoaded(order);
                    }
                });

        Log.i(TAG, "getOrders()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
