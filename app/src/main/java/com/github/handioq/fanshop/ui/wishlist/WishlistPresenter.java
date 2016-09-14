package com.github.handioq.fanshop.ui.wishlist;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.ProductListDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class WishlistPresenter implements WishlistMvp.Presenter, WishlistMvp.Model.Callback {

    private WishlistMvp.View view;
    private WishlistMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "WishlistPresenter";

    @Inject
    public WishlistPresenter(NetworkService networkService) {
        model = new WishlistModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void onWishlistLoaded(ProductListDVO products) {
        if (view != null) {
            view.setWishlist(products);
            view.hideLoadWishlistProgress();
        }
    }

    @Override
    public void onWishlistLoadError(Throwable error) {
        if (view != null) {
            view.showLoadWishlistError(error);
            view.hideLoadWishlistProgress();
        }
    }

    @Override
    public void onLoadWishlistCompleted() {
        if (view != null) {
            view.hideLoadWishlistProgress();
        }
    }

    @Override
    public void getWishlist(int userId) {
        if (view != null) {
            view.showLoadWishlistProgress();
            Log.i(TAG, "showProgress");
        }

        model.getWishlist(userId);
    }

    @Override
    public void setView(WishlistMvp.View view) {
        this.view = view;
    }
}
