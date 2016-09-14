package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

public class ReviewDTO {

    @SerializedName("message")
    private String message;

    @SerializedName("stars")
    private int stars;

    @SerializedName("author")
    private String author;

    public ReviewDTO() {
    }

    public ReviewDTO(String message, int stars) {
        this.message = message;
        this.stars = stars;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public int getStars() {
        return stars;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
