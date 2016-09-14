package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductIdDTO implements Serializable {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductIdDTO{" +
                "id=" + id +
                '}';
    }
}
