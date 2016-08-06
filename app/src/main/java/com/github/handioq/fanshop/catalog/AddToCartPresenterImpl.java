package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.Response;

import javax.inject.Inject;

public class AddToCartPresenterImpl implements AddToCartPresenter, AddToCartModel.Callback {

    private AddToCartView addToCartView;
    private AddToCartModel addToCartModel;
    private NetworkService networkService;

    @Inject
    public AddToCartPresenterImpl(NetworkService networkService) {
        this.networkService = networkService;

        addToCartModel = new AddToCartModelImpl(networkService);
        addToCartModel.setCallback(this);
    }

    @Override
    public void addProductToCart(int userId, ProductDTO productDTO) {
        addToCartModel.addProductToCart(userId, productDTO);
    }

    @Override
    public void setView(AddToCartView addToCartView) {
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
    public void onCompleted() {
        //addToCartView.onCompleted();
    }
}
