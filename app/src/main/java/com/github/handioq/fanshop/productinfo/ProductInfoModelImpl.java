package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.catalog.CatalogModel;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Observer;

public class ProductInfoModelImpl implements ProductInfoModel {

    private NetworkService networkService;
    private ProductInfoModel.Callback callback;

    private final static String TAG = "ProductInfoModelImpl";

    public ProductInfoModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProduct(int id) {

        networkService.getApiService()
                .getProduct(id)
                .compose(NetworkService.<Product>tranform())
                .subscribe(new Observer<Product>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductLoadError(e);
                    }

                    @Override
                    public void onNext(Product product) {
                        callback.onProductLoaded(product);
                    }
                });

        Log.e(TAG, "getProduct()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
