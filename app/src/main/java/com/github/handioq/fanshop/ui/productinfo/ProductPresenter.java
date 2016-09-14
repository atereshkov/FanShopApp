package com.github.handioq.fanshop.ui.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

import timber.log.Timber;

public class ProductPresenter implements ProductMvp.Presenter, ProductModel.Callback {

    private ProductMvp.View view;
    private ProductMvp.Model model;

    private final static String TAG = "ProductPresenter";

    @Inject
    public ProductPresenter(NetworkService networkService) {
        model = new ProductModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void setView(ProductMvp.View productInfoView) {
        this.view = productInfoView;
    }

    @Override
    public void getProduct(int productId, int userId) {
        if (view != null) {
            view.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on productInfoView");
        }

        model.getProduct(productId, userId);
    }

    @Override
    public void onProductLoaded(ProductDVO product) {
        if (view != null) {
            view.setProduct(product);
            view.hideProgress();
        }

        Timber.i("onProductLoaded: %s, id: %d", product.getName(), product.getId());
    }

    @Override
    public void onProductLoadError(Throwable error) {
        Log.e(TAG, error.toString());
        if (view != null) {
            view.onError(error);
            view.hideProgress();
        }
    }
}
