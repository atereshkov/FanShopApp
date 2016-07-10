package com.github.handioq.fanshop.login;

public class UserAuthState {

    private String response;
    private int code;

    public UserAuthState(String response, int code) {
        this.response = response;
        this.code = code;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "UserAuthState{" +
                "response='" + response + '\'' +
                ", code=" + code +
                '}';
    }
}
