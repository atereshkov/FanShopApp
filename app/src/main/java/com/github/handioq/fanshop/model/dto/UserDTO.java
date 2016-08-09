package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("amount_spent")
    private double amountSpent;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountSpent=" + amountSpent +
                '}';
    }
}
