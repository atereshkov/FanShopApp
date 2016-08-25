package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class ProductDVO {

    private int id;
    private String name;
    private double price;
    private String description;
    private boolean isUserFavorite;
    private String imageUrl;
    private List<ImageDVO> images = new ArrayList<ImageDVO>();
    private List<ReviewDVO> reviews = new ArrayList<>();

    public ProductDVO(int id, String name, double price, String description, boolean isUserFavorite, String imageUrl, List<ImageDVO> images, List<ReviewDVO> reviews) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isUserFavorite = isUserFavorite;
        this.imageUrl = imageUrl;
        this.images = images;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUserFavorite() {
        return isUserFavorite;
    }

    public void setUserFavorite(boolean userFavorite) {
        isUserFavorite = userFavorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ImageDVO> getImages() {
        return images;
    }

    public void setImages(List<ImageDVO> images) {
        this.images = images;
    }

    public List<ReviewDVO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDVO> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ProductDVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isUserFavorite=" + isUserFavorite +
                ", imageUrl='" + imageUrl + '\'' +
                ", images=" + images +
                ", reviews=" + reviews +
                '}';
    }
}
