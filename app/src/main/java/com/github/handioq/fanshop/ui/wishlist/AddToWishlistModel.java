package com.github.handioq.fanshop.ui.wishlist;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class AddToWishlistModel implements AddToWishlistMvp.Model {

    private final NetworkService networkService;
    private AddToWishlistModel.Callback callback;

    public AddToWishlistModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addProductToWishlist(int userId, ProductDTO product) {
        networkService.getApiService()
                .addProductToWishlist(userId, product)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onAddToWishlistCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWishlistAddError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProductAddedToWishlist(response);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
