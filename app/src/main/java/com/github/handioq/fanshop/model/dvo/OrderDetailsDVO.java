package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDVO {

    private int id;
    private String status;
    private String date;
    private List<ProductDVO> products = new ArrayList<>();

    public OrderDetailsDVO(int id, String status, String date, List<ProductDVO> products) {
        this.id = id;
        this.status = status;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductDVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDVO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "OrderDetailsDVO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", products=" + products +
                '}';
    }
}
