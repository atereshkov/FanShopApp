package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    Token token;

    @SerializedName("statusCode")
    private int statusCode;

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Token getToken() {
        return token;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "status='" + status + '\'' +
                ", statusCode=" + statusCode +
                ", token=" + token +
                '}';
    }
}
