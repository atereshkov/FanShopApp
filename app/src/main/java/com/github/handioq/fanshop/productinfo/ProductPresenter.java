package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

import timber.log.Timber;

public class ProductPresenter implements ProductMvp.Presenter, ProductModel.Callback {

    private ProductMvp.View productInfoView;
    private ProductMvp.Model productInfoModel;

    private final static String TAG = "ProductPresenter";

    @Inject
    public ProductPresenter(NetworkService networkService) {
        productInfoModel = new ProductModel(networkService);
        productInfoModel.setCallback(this);
    }

    @Override
    public void setView(ProductMvp.View productInfoView) {
        this.productInfoView = productInfoView;
    }

    @Override
    public void getProduct(int id) {
        if (productInfoView != null) {
            productInfoView.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on productInfoView");
        }

        productInfoModel.getProduct(id);
    }

    @Override
    public void onProductLoaded(ProductDVO product) {
        productInfoView.setProduct(product);
        productInfoView.hideProgress();
        Timber.i("onProductLoaded: %s, id: %d", product.getName(), product.getId());
    }

    @Override
    public void onProductLoadError(Throwable error) {
        Log.e(TAG, error.toString());
        productInfoView.onError(error);
        productInfoView.hideProgress();
    }
}
