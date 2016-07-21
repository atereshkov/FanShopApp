package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

public class CatalogModelImpl implements CatalogModel {

    private NetworkService networkService;

    @Override
    public void getProducts(int offset, int count) {
    }

    @Override
    public void setCallback(Callback callback) {
        //TODO Return listen observable
        networkService.getApiService()
                .getProducts("1", "1")
                .compose(NetworkService.<List<Product>>tranform());
    }

//    @Override
//    public Observable<List<Product>> getProducts() {
//        Log.e("CatalogModelImpl", "Starting products download");
//        return networkService.getPreparedObservable(networkService.getApiService().getProducts(null, null));
//    }

}
