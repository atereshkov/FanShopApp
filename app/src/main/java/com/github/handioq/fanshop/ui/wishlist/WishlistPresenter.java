package com.github.handioq.fanshop.ui.wishlist;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class WishlistPresenter implements WishlistMvp.Presenter, WishlistMvp.Model.Callback {

    private WishlistMvp.View wishlistView;
    private WishlistMvp.Model wishlistModel;
    private NetworkService networkService;

    private final static String TAG = "WishlistPresenter";

    @Inject
    public WishlistPresenter(NetworkService networkService) {
        wishlistModel = new WishlistModel(networkService);
        wishlistModel.setCallback(this);
    }

    @Override
    public void onWishlistLoaded(List<ProductDTO> products) {
        wishlistView.setWishlist(products);
        wishlistView.hideLoadWishlistProgress();
    }

    @Override
    public void onWishlistLoadError(Throwable error) {
        wishlistView.showLoadWishlistError(error);
        wishlistView.hideLoadWishlistProgress();
    }

    @Override
    public void onLoadWishlistCompleted() {
        wishlistView.hideLoadWishlistProgress();
    }

    @Override
    public void getWishlist(int userId) {
        if (wishlistView != null) {
            wishlistView.showLoadWishlistProgress();
            Log.i(TAG, "showProgress");
        }

        wishlistModel.getWishlist(userId);
    }

    @Override
    public void setView(WishlistMvp.View view) {
        this.wishlistView = view;
    }
}
