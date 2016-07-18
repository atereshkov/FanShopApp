package com.github.handioq.fanshop.catalog;


import android.os.Handler;

import com.github.handioq.fanshop.model.PaginationResponse;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class CatalogModelImpl implements CatalogModel {

    private NetworkService networkService;

    public CatalogModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<List<Product>> getProducts() {
        //return networkService.getPreparedObservable(networkService.getApiService().getProducts(page, count)); // just test with this values TODO: rework parameters calls
        return networkService.getPreparedObservable(networkService.getApiService().getProducts(null, null));
    }

}
