package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReviewListDTO {

    @SerializedName("data")
    private List<ReviewDTO> reviews = new ArrayList<>();

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ReviewListDTO{" +
                "reviews=" + reviews +
                ", status='" + status + '\'' +
                ", code=" + code +
                '}';
    }
}
