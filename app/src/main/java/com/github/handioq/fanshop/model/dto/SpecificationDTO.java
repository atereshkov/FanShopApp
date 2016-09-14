package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecificationDTO {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private SpecificationDataDTO data;

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

    public SpecificationDataDTO getData() {
        return data;
    }

    public void setData(SpecificationDataDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpecificationDTO{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
