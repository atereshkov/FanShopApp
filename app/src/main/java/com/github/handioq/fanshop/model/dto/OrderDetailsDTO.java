package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDTO {

    @SerializedName("id")
    private int id;

    @SerializedName("status")
    private String status;

    @SerializedName("date")
    private String date;

    @SerializedName("products")
    private List<ProductDTO> products = new ArrayList<>();

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", products=" + products +
                '}';
    }
}
