package com.github.handioq.fanshop.catalog;


import com.github.handioq.fanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public interface CatalogModel {

    Observable<List<Product>> getProducts(); // pagination..

}
