package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.dto.ProductDTO;
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
