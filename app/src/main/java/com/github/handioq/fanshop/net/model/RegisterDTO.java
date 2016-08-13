package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class RegisterDTO {

    @SerializedName("login")
    String login;

    @SerializedName("password")
    String password;

    public RegisterDTO(String login, String password) {
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
        return "RegisterDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
