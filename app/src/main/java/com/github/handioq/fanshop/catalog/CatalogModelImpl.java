package com.github.handioq.fanshop.catalog;


import android.os.Handler;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

public class CatalogModelImpl implements CatalogModel {

    private NetworkService networkService;

    public CatalogModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<List<Product>> getProducts() {

        /*new Handler().postDelayed(new Runnable() {
            @Override public void run() {

            }
        }, 2000);*/

        return networkService.getPreparedObservable(networkService.getApiService().getProducts());
    }

    /*private List<Product> createMockList() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Name 1", 20.5));
        products.add(new Product(2, "Name 2", 109.7));
        products.add(new Product(3, "Name 3", 10.0));

        return products;
    }*/

}
