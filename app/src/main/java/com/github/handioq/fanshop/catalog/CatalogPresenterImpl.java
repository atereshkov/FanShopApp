package com.github.handioq.fanshop.catalog;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class CatalogPresenterImpl implements CatalogPresenter, CatalogModel.Callback {

    private CatalogView catalogView;
    private CatalogModel catalogModel;
    private NetworkService networkService;

    private final static String TAG = "CatalogPresenterImpl";

    @Inject
    public CatalogPresenterImpl(NetworkService networkService) {
        catalogModel = new CatalogModelImpl(networkService);
        catalogModel.setCallback(this);
    }

    @Override
    public void setView(CatalogView catalogView) {
        this.catalogView = catalogView;
    }

    @Override
    public void getProducts(int offset, int limit) {
        if (catalogView != null) {
            catalogView.showProgress();
            Log.i(TAG, "showProgress() on catalogView");
        }

        catalogModel.getProducts(offset, limit);
    }

    @Override
    public void onItemClicked(View view, int position) {
        catalogView.onItemClicked(view, position);
    }

    @Override
    public void onCompleted() {
        catalogView.hideProgress();
    }

    @Override
    public void onProductsLoaded(List<ProductDTO> productDTOs) {
        Log.i(TAG, "get productDTOs: " + productDTOs.size());
        // TODO add to database and check for duplicates

        //IProductRepository<ProductDBO> productRepository = new ProductRepository();
        //productRepository.addProduct(, this); productDBO? product?

        catalogView.setProducts(productDTOs);
        catalogView.hideProgress();
    }

    @Override
    public void onProductsLoadError(Throwable error) {
        catalogView.onError(error);
        catalogView.hideProgress();
        Log.i(TAG, "onError");
    }
}
