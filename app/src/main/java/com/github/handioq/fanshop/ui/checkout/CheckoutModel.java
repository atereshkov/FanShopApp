package com.github.handioq.fanshop.ui.checkout;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductIdDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import java.util.List;

import rx.Subscriber;

public class CheckoutModel implements CheckoutMvp.Model {

    private final NetworkService networkService;
    private CheckoutMvp.Model.Callback callback;

    private final static String TAG = "CheckoutModel";

    public CheckoutModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void createOrder(int userId, List<ProductIdDTO> products) {

        networkService.getApiService()
                .createOrder(userId, products)
                //.map(Mapper::mapProductListToDvo)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onOrderCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onOrderCreateError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onOrderCreated(response);
                    }
                });

        Log.i(TAG, "createOrder");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
