package com.github.handioq.fanshop.ui.wishlist.interaction;

import android.util.Log;

import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class RemoveWishlistPresenter implements RemoveWishlistMvp.Presenter, RemoveWishlistMvp.Model.Callback {

    private static final String TAG = "RemoveWishlistPresenter";

    private RemoveWishlistMvp.View removeView;
    private RemoveWishlistMvp.Model removeModel;
    private NetworkService networkService;

    @Inject
    public RemoveWishlistPresenter(NetworkService networkService) {
        this.networkService = networkService;

        removeModel = new RemoveWishlistModel(networkService);
        removeModel.setCallback(this);
    }

    @Override
    public void onProductRemovedFromWishlist() {
        Log.i(TAG, "onProductRemovedFromWishlist");
        removeView.onProductRemovedFromWishlist();
    }

    @Override
    public void onWishlistRemoveError(Throwable error) {
        Log.e(TAG, error.toString());
        removeView.onWishlistRemoveError(error);
    }

    @Override
    public void onRemoveFromWishlistCompleted() {

    }

    @Override
    public void removeProduct(int userId, int productId) {
        removeModel.removeProduct(userId, productId);
    }

    @Override
    public void setView(RemoveWishlistMvp.View view) {
        this.removeView = view;
    }
}
