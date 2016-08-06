package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;

public interface AddToCartPresenter {

    void addProductToCart(int userId, ProductDTO productDTO);

    void setView(AddToCartView addToCartView);

}
