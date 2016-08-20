package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Subscriber;

public class ProductInfoModel implements ProductMvp.Model {

    private final NetworkService networkService;
    private ProductInfoModel.Callback callback;

    private final static String TAG = "ProductInfoModel";

    public ProductInfoModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProduct(int id) {

        networkService.getApiService()
                .getProduct(id)
                .compose(NetworkService.<ProductDTO>applyScheduler())
                .subscribe(new Subscriber<ProductDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductLoadError(e);
                    }

                    @Override
                    public void onNext(ProductDTO productDTO) {
                        callback.onProductLoaded(productDTO);
                    }
                });

        Log.i(TAG, "getProduct()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
