package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class ProductInfoDTO {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private ProductDTO data;

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

    public ProductDTO getData() {
        return data;
    }

    public void setData(ProductDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductInfoDTO{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
