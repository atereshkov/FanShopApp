package com.github.handioq.fanshop.model;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public class PaginationResponse {

    private Integer current;
    private Integer next;
    private List<ProductDTO> productDTOs;

    public PaginationResponse(Integer current, Integer next, List<ProductDTO> productDTOs) {
        this.current = current;
        this.next = next;
        this.productDTOs = productDTOs;
    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getNext() {
        return next;
    }

    public List<ProductDTO> getProductDTOs() {
        return productDTOs;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public void setProductDTOs(List<ProductDTO> productDTOs) {
        this.productDTOs = productDTOs;
    }
}
