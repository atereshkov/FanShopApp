package com.github.handioq.fanshop.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassOrderDTO implements Serializable {

    private List<ProductDTO> products = new ArrayList<>();

    public PassOrderDTO(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
