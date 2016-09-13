package com.github.handioq.fanshop.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassOrderDTO implements Serializable {

    private List<ProductIdDTO> products = new ArrayList<>();

    public PassOrderDTO(List<ProductIdDTO> products) {
        this.products = products;
    }

    public List<ProductIdDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductIdDTO> products) {
        this.products = products;
    }
}
