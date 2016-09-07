package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class ProductListDVO {

    private List<ProductDVO> products = new ArrayList<>();

    public ProductListDVO() {
    }

    public ProductListDVO(List<ProductDVO> products) {
        this.products = products;
    }

    public List<ProductDVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDVO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductListDVO{" +
                "products=" + products +
                '}';
    }
}
