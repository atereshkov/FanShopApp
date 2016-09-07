package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductListDTO {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<ProductDTO> products = new ArrayList<>();

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ProductListDTO{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", products=" + products +
                '}';
    }
}
