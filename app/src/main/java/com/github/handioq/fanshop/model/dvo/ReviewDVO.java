package com.github.handioq.fanshop.model.dvo;

public class ReviewDVO {

    private String message;
    private int stars;

    public ReviewDVO(String message, int stars) {
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

    @Override
    public String toString() {
        return "ReviewDVO{" +
                "message='" + message + '\'' +
                ", stars=" + stars +
                '}';
    }
}
