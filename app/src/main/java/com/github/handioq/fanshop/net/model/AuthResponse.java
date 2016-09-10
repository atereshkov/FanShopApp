package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private AuthResponseData responseData;

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

    public AuthResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(AuthResponseData responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", responseData=" + responseData +
                '}';
    }
}
