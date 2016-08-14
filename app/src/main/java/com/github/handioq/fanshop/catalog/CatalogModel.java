package com.github.handioq.fanshop.catalog;


import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

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
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<List<ProductDTO>>applyScheduler())
                .subscribe(new Subscriber<List<ProductDTO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadProductsCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ProductDTO> productDTOs) {
                        callback.onProductsLoaded(productDTOs);
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
