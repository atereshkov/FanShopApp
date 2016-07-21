package com.github.handioq.fanshop.catalog;

import android.util.Log;

import com.github.handioq.fanshop.model.Product;

import java.util.List;

public class CatalogPresenterImpl implements CatalogPresenter, CatalogModel.Callback {

    private CatalogView catalogView;
    private CatalogModel catalogModel;

    private final static String TAG = "CatalogPresenterImpl";

    public CatalogPresenterImpl() {
        // TODO init catalog model
    }

    public void setCatalogView(CatalogView catalogView) {
        this.catalogView = catalogView;
        catalogModel.setCallback(this);
    }

    @Override
    public void getProducts() {
        if (catalogView != null) {
            catalogView.showProgress();
            Log.e(TAG, "showProgress() on catalogView");
        }

        catalogModel = new CatalogModelImpl();
        catalogModel.getProducts(0, 20);
    }

    @Override
    public void onItemClicked(int position) {
        // catalogView.onItemClick(position); ...
    }

    @Override
    public void onProductLoaded(List<Product> products) {
        catalogView.setProducts(products);
        catalogView.hideProgress();
        Log.e(TAG, "onNext, get products: " + products.size());
    }

    @Override
    public void onProductsLoadError(Exception error) {
        catalogView.onError(error);
        catalogView.hideProgress();
        Log.e(TAG, "onError");
    }
}
