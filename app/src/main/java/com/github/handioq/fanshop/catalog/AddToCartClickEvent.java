package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;

public class AddToCartClickEvent {

    public final ProductDTO product;

    public AddToCartClickEvent(ProductDTO product) {
        this.product = product;
    }
}
