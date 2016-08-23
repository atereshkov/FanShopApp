package com.github.handioq.fanshop.cart.interaction;

public class RemoveFromCartEvent {

    private final int productId;

    public RemoveFromCartEvent(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}
