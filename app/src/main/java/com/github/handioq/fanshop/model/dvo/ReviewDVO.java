package com.github.handioq.fanshop.model.dvo;

public class ReviewDVO {

    private String message;
    private int stars;
    private String author;

    public ReviewDVO(String message, int stars, String author) {
        this.message = message;
        this.stars = stars;
        this.author = author;
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

    @Override
    public String toString() {
        return "ReviewDVO{" +
                "message='" + message + '\'' +
                ", stars=" + stars +
                ", author='" + author + '\'' +
                '}';
    }
}
