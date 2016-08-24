package com.github.handioq.fanshop.ui.cart.interaction;

public class RemoveFromCartEvent {

    private final int productId;

    public RemoveFromCartEvent(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}
