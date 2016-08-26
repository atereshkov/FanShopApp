package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("status")
    String status;

    @SerializedName("data")
    TokenData tokenData;

    @SerializedName("statusCode")
    int statusCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TokenData getTokenData() {
        return tokenData;
    }

    public void setTokenData(TokenData tokenData) {
        this.tokenData = tokenData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Token{" +
                "status='" + status + '\'' +
                ", tokenData=" + tokenData +
                ", statusCode=" + statusCode +
                '}';
    }
}
