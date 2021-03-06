package com.github.handioq.fanshop.ui.cart.interaction;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import javax.inject.Inject;

public class AddToCartPresenter implements AddToCartMvp.Presenter, AddToCartMvp.Model.Callback {

    private AddToCartMvp.View addToCartView;
    private AddToCartModel addToCartModel;
    private NetworkService networkService;

    @Inject
    public AddToCartPresenter(NetworkService networkService) {
        this.networkService = networkService;

        addToCartModel = new AddToCartModel(networkService);
        addToCartModel.setCallback(this);
    }

    @Override
    public void addProductToCart(int userId, int productId) {
        addToCartModel.addProductToCart(userId, productId);
    }

    @Override
    public void setView(AddToCartMvp.View addToCartView) {
        this.addToCartView = addToCartView;
    }

    @Override
    public void onProductAdded(Response response) {
        addToCartView.onProductAddSuccess(response);
    }

    @Override
    public void onProductAddError(Throwable error) {
        addToCartView.onProductAddError(error);
    }

    @Override
    public void onAddToCartCompleted() {
        //addToCartView.onAddToCartCompleted();
    }
}
