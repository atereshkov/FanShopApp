package com.github.handioq.fanshop.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    // id?

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
