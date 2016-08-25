package com.github.handioq.fanshop.model.dto;

public class ReviewDTO {

    private String message;
    private int stars;

    public ReviewDTO(String message, int stars) {
        this.message = message;
        this.stars = stars;
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
