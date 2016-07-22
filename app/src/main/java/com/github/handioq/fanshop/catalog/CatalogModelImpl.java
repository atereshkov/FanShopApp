package com.github.handioq.fanshop.catalog;


import android.util.Log;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Observer;
import rx.Subscription;

public class CatalogModelImpl implements CatalogModel {

    private NetworkService networkService;
    private Callback callback;

    private final static String TAG = "CatalogModelImpl";

    public CatalogModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProducts(int offset, int count) {

        networkService.getApiService()
                .getProducts(offset, count)
                .compose(NetworkService.<List<Product>>tranform())
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductsLoadError(e);
                    }

                    @Override
                    public void onNext(List<Product> products) {
                        callback.onProductLoaded(products);
                    }
                });

        Log.e(TAG, "getProducts()");
    }

    @Override
    public void setCallback(final Callback callback) {
        this.callback = callback;

        Log.e(TAG, "setCallback");
    }

//    @Override
//    public Observable<List<Product>> getProducts() {
//        Log.e("CatalogModelImpl", "Starting products download");
//        return networkService.getPreparedObservable(networkService.getApiService().getProducts(null, null));
//    }

}
