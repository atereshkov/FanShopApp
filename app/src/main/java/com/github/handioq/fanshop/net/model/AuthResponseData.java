package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponseData {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponseData{" +
                "token='" + token + '\'' +
                '}';
    }
}
