package com.github.handioq.fanshop.ui.cart;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

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
                .map(Mapper::mapProductListToDvo)
                .compose(NetworkService.<ProductListDVO>applyScheduler())
                .subscribe(new Subscriber<ProductListDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductsLoadError(e);
                    }

                    @Override
                    public void onNext(ProductListDVO products) {
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
