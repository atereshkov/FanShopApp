package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class OrderListDVO {

    private List<OrderDVO> orders = new ArrayList<>();

    public OrderListDVO(List<OrderDVO> orders) {
        this.orders = orders;
    }

    public OrderListDVO() {
    }

    public List<OrderDVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDVO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderListDTO{" +
                "orders=" + orders +
                '}';
    }

}
