package com.github.handioq.fanshop.catalog;


import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Observer;

public class CatalogModelImpl implements CatalogModel {

    private NetworkService networkService;
    private CatalogModel.Callback callback;

    private final static String TAG = "CatalogModelImpl";

    public CatalogModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getProducts(int offset, int count) {

        networkService.getApiService()
                .getProducts(offset, count)
                .compose(NetworkService.<List<ProductDTO>>tranform())
                .subscribe(new Observer<List<ProductDTO>>() {
                    @Override
                    public void onCompleted() {

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

        Log.e(TAG, "getProductDTOs()");
    }

    @Override
    public void setCallback(final Callback callback) {
        this.callback = callback;

        Log.e(TAG, "setCallback");
    }

//    @Override
//    public Observable<List<ProductDTO>> getProductDTOs() {
//        Log.e("CatalogModelImpl", "Starting products download");
//        return networkService.getPreparedObservable(networkService.getApiService().getProductDTOs(null, null));
//    }

}
