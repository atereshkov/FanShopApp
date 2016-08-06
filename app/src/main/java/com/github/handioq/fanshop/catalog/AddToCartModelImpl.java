package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.Response;

import rx.Observer;

public class AddToCartModelImpl implements AddToCartModel {

    private NetworkService networkService;
    private AddToCartModel.Callback callback;

    public AddToCartModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void addProductToCart(int userId, ProductDTO productDTO) {
        networkService.getApiService()
                .addProductToCart(userId, productDTO)
                //.delay(3, TimeUnit.SECONDS)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Observer<Response>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductAddError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        callback.onProductAdded(response);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
