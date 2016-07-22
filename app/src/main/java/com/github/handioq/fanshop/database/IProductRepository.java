package com.github.handioq.fanshop.database;

import java.util.List;

public interface IProductRepository<T> {

    void addProduct(T product, Callback<T> callback);

    void deleteProductById(int id, Callback<T> callback);

    void getProductById(int id, Callback<T> callback);

    void getProducts(Callback<T> callback);

    interface Callback<T> {

        void onSuccess();

        void onSuccess(T product);

        void onSuccess(List<T> productList);

    }

}
