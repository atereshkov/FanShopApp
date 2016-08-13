package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class LoginDTO {

    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
