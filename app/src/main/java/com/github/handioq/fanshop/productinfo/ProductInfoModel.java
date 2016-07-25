package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.model.Product;

import java.util.List;

public interface ProductInfoModel {

    void getProduct(int id);

    void setCallback(Callback callback);

    interface Callback {

        void onProductLoaded(Product product);

        void onProductLoadError(Throwable error);
    }

}
