package com.github.handioq.fanshop.cart;

import android.util.Log;

import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class RemoveFromCartPresenter implements RemoveFromCartMvp.Presenter, RemoveFromCartModel.Callback {

    private static final String TAG = "RemoveFromCartPresenter";

    private RemoveFromCartMvp.View removeView;
    private RemoveFromCartMvp.Model removeModel;

    @Inject
    public RemoveFromCartPresenter(NetworkService networkService) {
        removeModel = new RemoveFromCartModel(networkService);
        removeModel.setCallback(this);
    }

    @Override
    public void removeProductFromCart(int userId, int productId) {
        removeModel.removeProduct(userId, productId);
    }

    @Override
    public void setView(RemoveFromCartMvp.View view) {
        this.removeView = view;
    }

    @Override
    public void onProductRemoved() {
        Log.i(TAG, "onProductRemoved");
        removeView.onProductRemoveSuccess();
    }

    @Override
    public void onProductRemoveError(Throwable error) {
        Log.e(TAG, error.toString());
        removeView.onProductRemoveError(error);
    }

    @Override
    public void onRemoveCompleted() {

    }
}
