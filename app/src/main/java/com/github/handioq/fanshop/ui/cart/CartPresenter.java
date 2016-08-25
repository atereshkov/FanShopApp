package com.github.handioq.fanshop.ui.cart;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class CartPresenter implements CartMvp.Presenter, CartMvp.Model.Callback {

    private CartMvp.View cartView;
    private CartMvp.Model cartModel;
    private NetworkService networkService;

    private final static String TAG = "CartPresenter";

    @Inject
    public CartPresenter(NetworkService networkService) {
        cartModel = new CartModel(networkService);
        cartModel.setCallback(this);
    }

    @Override
    public void setView(CartMvp.View cartView) {
        this.cartView = cartView;
    }

    @Override
    public void onProductsLoaded(List<ProductDVO> products) {
        cartView.setCartItems(products);
    }

    @Override
    public void onProductsLoadError(Throwable error) {
        cartView.onError(error);
    }

    @Override
    public void onCompleted() {
        cartView.hideProgress();
    }

    @Override
    public void getCartItems(int userId) {
        if (cartView != null) {
            cartView.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on cartView");
        }

        cartModel.getCartItems(userId);
    }
}
