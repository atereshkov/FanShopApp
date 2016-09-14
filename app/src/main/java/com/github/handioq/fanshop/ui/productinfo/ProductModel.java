package com.github.handioq.fanshop.ui.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import rx.Subscriber;

public class ProductModel implements ProductMvp.Model {

    private final NetworkService networkService;
    private ProductModel.Callback callback;

    private final static String TAG = "ProductModel";

    public ProductModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProduct(int productId, int userId) {

        networkService.getApiService()
                .getProduct(productId, userId)
                .map(Mapper::mapProductInfoToDvo)
                .compose(NetworkService.<ProductDVO>applyScheduler())
                .subscribe(new Subscriber<ProductDVO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductLoadError(e);
                    }

                    @Override
                    public void onNext(ProductDVO product) {
                        callback.onProductLoaded(product);
                    }
                });

        Log.i(TAG, "getProduct()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
