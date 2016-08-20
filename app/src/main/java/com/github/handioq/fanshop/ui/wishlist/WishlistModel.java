package com.github.handioq.fanshop.ui.wishlist;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Subscriber;

public class WishlistModel implements WishlistMvp.Model {

    private final NetworkService networkService;
    private WishlistMvp.Model.Callback callback;

    private final static String TAG = "WishlistModel";

    public WishlistModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getWishlist(int userId) {

        networkService.getApiService()
                .getWishlist(userId)
                .compose(NetworkService.<List<ProductDTO>>applyScheduler())
                .subscribe(new Subscriber<List<ProductDTO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onLoadWishlistCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWishlistLoadError(e);
                    }

                    @Override
                    public void onNext(List<ProductDTO> products) {
                        callback.onWishlistLoaded(products);
                    }
                });

        Log.i(TAG, "getWishlist()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
