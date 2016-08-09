package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class AuthDTO {

    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    public AuthDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
