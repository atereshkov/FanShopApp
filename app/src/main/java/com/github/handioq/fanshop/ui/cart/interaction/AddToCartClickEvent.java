package com.github.handioq.fanshop.ui.cart.interaction;

import com.github.handioq.fanshop.model.dvo.ProductDVO;

public class AddToCartClickEvent {

    private final ProductDVO product;

    public AddToCartClickEvent(ProductDVO product) {
        this.product = product;
    }

    public ProductDVO getProduct() {
        return product;
    }
}
