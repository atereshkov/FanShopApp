package com.github.handioq.fanshop.database;

import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.model.dbo.ProductDBO;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ProductRepository implements IProductRepository <ProductDBO> { // ??? Transfom? which object used?

    @Override
    public void addProduct(ProductDBO product, Callback<ProductDBO> callback) {
        Realm realm = FanShopApp.getInstance().getRealm();

        realm.beginTransaction();

        ProductDBO productDBO = realm.createObject(ProductDBO.class);
        productDBO.setId(product.getId());
        productDBO.setImageUrl(product.getImageUrl());
        productDBO.setName(product.getName());
        productDBO.setPrice(product.getPrice());

        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess(product);
    }

    @Override
    public void deleteProductById(int id, Callback<ProductDBO> callback) {
        Realm realm = FanShopApp.getInstance().getRealm();

        realm.beginTransaction();

        RealmQuery query = realm.where(ProductDBO.class);
        RealmResults results = query.findAll();
        results.deleteFromRealm(id);

        realm.commitTransaction();

        if (callback != null)
            callback.onSuccess();
    }

    @Override
    public void getProductById(int id, Callback<ProductDBO> callback) {
        Realm realm = FanShopApp.getInstance().getRealm();

        ProductDBO result = realm.where(ProductDBO.class).equalTo("id", id).findFirst();

        if (callback != null)
            callback.onSuccess(result);
    }

    @Override
    public void getProducts(Callback<ProductDBO> callback) {
        Realm realm = FanShopApp.getInstance().getRealm();

        RealmQuery query = realm.where(ProductDBO.class);
        RealmResults results = query.findAll();

        /*if (callback != null)
            callback.onSuccess(results);*/
    }
}
