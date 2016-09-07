package com.github.handioq.fanshop.ui.catalog;


import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import rx.Subscriber;

public class CatalogModel implements CatalogMvp.Model {

    private final NetworkService networkService;
    private CatalogMvp.Model.Callback callback;

    private final static String TAG = "CatalogModel";

    public CatalogModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProducts(int category, int offset, int count) {

        networkService.getApiService()
                .getProducts(category, offset, count)
                .map(Mapper::mapProductListToDvo)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<ProductListDVO>applyScheduler())
                .subscribe(new Subscriber<ProductListDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadProductsCompleted();
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

        Log.i(TAG, "getProductDTOs()");
    }

    @Override
    public void setCallback(final Callback callback) {
        this.callback = callback;

        Log.i(TAG, "setCallback");
    }

}
