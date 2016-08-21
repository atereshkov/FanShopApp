package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class SizeDTO {

    @SerializedName("size")
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
