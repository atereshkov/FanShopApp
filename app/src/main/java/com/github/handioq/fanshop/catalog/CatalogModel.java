package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.Product;

import java.util.List;

public interface CatalogModel {

    void getProducts(int offset, int count);

    void setCallback(Callback callback);

    interface Callback {

        void onProductLoaded(List<Product> products);

        void onProductsLoadError(Exception error);
    }

}
