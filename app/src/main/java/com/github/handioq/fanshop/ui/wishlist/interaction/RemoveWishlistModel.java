package com.github.handioq.fanshop.ui.wishlist.interaction;

import android.util.Log;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class RemoveWishlistModel implements RemoveWishlistMvp.Model {

    private final NetworkService networkService;
    private RemoveWishlistMvp.Model.Callback callback;

    private final static String TAG = "RemoveWishlistModel";

    public RemoveWishlistModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void removeProduct(int userId, int productId) {
        networkService.getApiService()
                .removeProductFromWishlist(userId, productId)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onRemoveFromWishlistCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWishlistRemoveError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProductRemovedFromWishlist();
                    }
                });
        Log.i(TAG, "removeProduct");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
