package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogModelImpl implements CatalogModel{

    @Override
    public void findItems(OnFinishedListener listener) {
        listener.onFinished(createMockList());
    }

    private List<Product> createMockList() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Name 1", 20.5));
        products.add(new Product(2, "Name 2", 109.7));
        products.add(new Product(3, "Name 3", 10.0));

        return products;
    }

}
