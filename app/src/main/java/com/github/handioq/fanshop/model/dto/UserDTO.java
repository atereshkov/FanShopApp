package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    // id?

    public UserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
