package com.github.handioq.fanshop.ui.wishlist;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

public class AddToWishlistPresenter implements AddToWishlistMvp.Presenter, AddToWishlistMvp.Model.Callback {

    private AddToWishlistMvp.View addToWishlistView;
    private AddToWishlistMvp.Model addToWishlistModel;
    private NetworkService networkService;

    public AddToWishlistPresenter(NetworkService networkService) {
        addToWishlistModel = new AddToWishlistModel(networkService);
        addToWishlistModel.setCallback(this);
    }

    @Override
    public void onProductAddedToWishlist(Response response) {
        addToWishlistView.onProductAddedToWishlist(response);
    }

    @Override
    public void onWishlistAddError(Throwable error) {
        addToWishlistView.onWishlistAddError(error);
    }

    @Override
    public void onAddToWishlistCompleted() {

    }

    @Override
    public void addProductToWishlist(int userId, ProductDTO product) {
        addToWishlistModel.addProductToWishlist(userId, product);
    }

    @Override
    public void setView(AddToWishlistMvp.View view) {
        this.addToWishlistView = view;
    }
}
