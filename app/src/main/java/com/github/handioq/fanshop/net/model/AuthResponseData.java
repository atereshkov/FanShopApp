package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponseData {

    @SerializedName("token")
    private String token;

    @SerializedName("userId")
    private int userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AuthResponseData{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
