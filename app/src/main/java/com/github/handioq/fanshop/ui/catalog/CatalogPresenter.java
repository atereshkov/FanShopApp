package com.github.handioq.fanshop.ui.catalog;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class CatalogPresenter implements CatalogMvp.Presenter, CatalogMvp.Model.Callback {

    private CatalogMvp.View catalogView;
    private CatalogMvp.Model catalogModel;
    private NetworkService networkService;

    private final static String TAG = "CatalogPresenter";

    @Inject
    public CatalogPresenter(NetworkService networkService) {
        catalogModel = new CatalogModel(networkService);
        catalogModel.setCallback(this);
    }

    @Override
    public void setView(CatalogMvp.View catalogView) {
        this.catalogView = catalogView;
    }

    @Override
    public void getProducts(int category, int offset, int limit) {
        if (catalogView != null) {
            catalogView.showLoadProductsProgress();
            Log.i(TAG, "showLoadProductsProgress() on catalogView");
        }

        catalogModel.getProducts(category, offset, limit);
    }

    /*
    @Override
    public void onItemClicked(View view, int position) {
        catalogView.onItemClicked(view, position);
    }*/

    @Override
    public void onLoadProductsCompleted() {
        catalogView.hideLoadProductsProgress();
    }

    @Override
    public void onProductsLoaded(ProductListDVO products) {
        Log.i(TAG, "get productDTOs: " + products.getProducts().size());
        // TODO add to database and check for duplicates

        //IProductRepository<ProductDBO> productRepository = new ProductRepository();
        //productRepository.addProduct(, this); productDBO? product?

        catalogView.setProducts(products);
        catalogView.hideLoadProductsProgress();
    }

    @Override
    public void onProductsLoadError(Throwable error) {
        catalogView.showLoadProductsError(error);
        catalogView.hideLoadProductsProgress();
        Log.i(TAG, "onError");
    }
}
