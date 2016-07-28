package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

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
                .compose(NetworkService.<ProductDTO>tranform())
                .subscribe(new Observer<ProductDTO>() {
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

        Log.e(TAG, "getProduct()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
