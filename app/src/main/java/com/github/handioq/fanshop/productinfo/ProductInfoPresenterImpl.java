package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

public class ProductInfoPresenterImpl implements ProductInfoPresenter, ProductInfoModel.Callback {

    private ProductInfoView productInfoView;
    private ProductInfoModel productInfoModel;

    private NetworkService networkService;

    private final static String TAG = "ProductInfoPresenterImp";

    public ProductInfoPresenterImpl(ProductInfoView productInfoView, NetworkService networkService) {
        this.productInfoView = productInfoView;
        this.networkService = networkService;

        productInfoModel = new ProductInfoModelImpl(networkService);
        productInfoModel.setCallback(this);
    }

    @Override
    public void getProduct(int id) { // TODO change presenter parameters
        if (productInfoView != null) {
            productInfoView.showProgress();
            Log.e(TAG, "showProgress() on productInfoView");
        }

        productInfoModel.getProduct(id);
    }

    @Override
    public void onProductLoaded(Product product) {
        productInfoView.setProduct(product);
        productInfoView.hideProgress();
        Log.e(TAG, "get product: " + product.getName() + " " + product.getId());
    }

    @Override
    public void onProductLoadError(Throwable error) {
        productInfoView.onError(error);
        productInfoView.hideProgress();
        Log.e(TAG, "onError");
    }
}
