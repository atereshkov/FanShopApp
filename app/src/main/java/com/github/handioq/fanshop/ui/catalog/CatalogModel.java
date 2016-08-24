package com.github.handioq.fanshop.ui.catalog;


import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import java.util.List;

import rx.Subscriber;

public class CatalogModel implements CatalogMvp.Model {

    private final NetworkService networkService;
    private CatalogMvp.Model.Callback callback;

    private final static String TAG = "CatalogModel";

    public CatalogModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProducts(String category, int offset, int count) {

        networkService.getApiService()
                .getProducts(category, offset, count)
                .map(Mapper::mapProductsToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<List<ProductDVO>>applyScheduler())
                .subscribe(new Subscriber<List<ProductDVO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadProductsCompleted();
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

        Log.i(TAG, "getProductDTOs()");
    }

    @Override
    public void setCallback(final Callback callback) {
        this.callback = callback;

        Log.i(TAG, "setCallback");
    }

}
