package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class TokenData {

    @SerializedName("tokenName")
    String token;

    @SerializedName("creationDate")
    private long creationDate;

    @SerializedName("expirationDate")
    private long expirationDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }


}
