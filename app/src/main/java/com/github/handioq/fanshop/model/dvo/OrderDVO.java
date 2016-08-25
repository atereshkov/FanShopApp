package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class OrderDVO {

    private int id;
    private String status;
    private List<ProductDVO> products = new ArrayList<>();

    public OrderDVO(int id, String status, List<ProductDVO> products) {
        this.id = id;
        this.status = status;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDVO> products) {
        this.products = products;
    }
}
