package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class ReviewListDVO {

    List<ReviewDVO> reviews = new ArrayList<>();

    public ReviewListDVO(List<ReviewDVO> reviews) {
        this.reviews = reviews;
    }

    public ReviewListDVO() {
    }

    public List<ReviewDVO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDVO> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ReviewListDVO{" +
                "reviews=" + reviews +
                '}';
    }
}
