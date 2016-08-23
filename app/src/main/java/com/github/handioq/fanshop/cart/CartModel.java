package com.github.handioq.fanshop.cart;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class CartModel implements CartMvp.Model {

    private final NetworkService networkService;
    private CartMvp.Model.Callback callback;

    private final static String TAG = "CartModel";

    public CartModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getCartItems(int userId) {

        networkService.getApiService()
                .getCart(userId)
                .map(Mapper::mapProductsToDvo)
                .compose(NetworkService.<List<ProductDVO>>applyScheduler())
                .subscribe(new Subscriber<List<ProductDVO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ProductDVO> products) {
                        callback.onProductsLoaded(products);
                    }
                });

        Log.i(TAG, "getCartItems()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
