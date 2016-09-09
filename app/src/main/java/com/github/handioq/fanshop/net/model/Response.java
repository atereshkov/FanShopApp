package com.github.handioq.fanshop.net.model;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("code")
    int statusCode;

    @SerializedName("message")
    String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", message ='" + statusMessage + '\'' +
                '}';
    }
}
