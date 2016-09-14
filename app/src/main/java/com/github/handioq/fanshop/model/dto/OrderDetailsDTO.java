package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDTO {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private OrderDataDTO data;

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

    public OrderDataDTO getData() {
        return data;
    }

    public void setData(OrderDataDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
