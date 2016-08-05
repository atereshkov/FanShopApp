package com.github.handioq.fanshop.cart;

import android.util.Log;
import android.view.View;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class CartPresenterImpl implements CartPresenter, CartModel.Callback {

    private CartView cartView;
    private CartModel cartModel;
    private NetworkService networkService;

    private final static String TAG = "CartPresenterImpl";

    @Inject
    public CartPresenterImpl(NetworkService networkService) {
        cartModel = new CartModelImpl(networkService);
        cartModel.setCallback(this);
    }

    @Override
    public void setView(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void onProductsLoaded(List<ProductDTO> productDTOs) {
        cartView.setCartItems(productDTOs);
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
            Log.i(TAG, "showProgress() on cartView");
        }

        cartModel.gerCartItems(userId);
    }

    @Override
    public void onItemClicked(View view, int position) {
        cartView.onItemClicked(view, position);
    }
}
