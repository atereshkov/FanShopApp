package com.github.handioq.fanshop.ui.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.OrderDVO;
import com.github.handioq.fanshop.model.dvo.OrderListDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class OrderModel implements OrderMvp.Model {

    private final NetworkService networkService;
    private OrderMvp.Model.Callback callback;

    private final static String TAG = "OrderModel";

    public OrderModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getOrders(int userId) {
        networkService.getApiService()
                .getOrders(userId)
                .map(Mapper::mapOrdersToDvo)
                .compose(NetworkService.<OrderListDVO>applyScheduler())
                .subscribe(new Subscriber<OrderListDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrdersLoadError(e);
                    }

                    @Override
                    public void onNext(OrderListDVO order) {
                        callback.onOrdersLoaded(order);
                    }
                });

        Log.i(TAG, "getCategories()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
