package com.github.handioq.fanshop.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    String mLogin;

    @SerializedName("password")
    String mPassword;

    // id?

    public User(String mLogin, String mPassword) {
        this.mLogin = mLogin;
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "mLogin='" + mLogin + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
