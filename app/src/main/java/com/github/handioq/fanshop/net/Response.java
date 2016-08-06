package com.github.handioq.fanshop.net;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status_code")
    int code;

    @SerializedName("status_message")
    String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
