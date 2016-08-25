package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    @SerializedName("id")
    private int id;

    @SerializedName("status")
    private String status;

    @SerializedName("products")
    private List<ProductDTO> products = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
