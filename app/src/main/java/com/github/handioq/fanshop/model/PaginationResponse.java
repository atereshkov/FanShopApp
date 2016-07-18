package com.github.handioq.fanshop.model;

import java.util.List;

public class PaginationResponse {

    private Integer current;
    private Integer next;
    private List<Product> products;

    public PaginationResponse(Integer current, Integer next, List<Product> products) {
        this.current = current;
        this.next = next;
        this.products = products;
    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getNext() {
        return next;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
