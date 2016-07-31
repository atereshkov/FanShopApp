package com.github.handioq.fanshop.model.dbo;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductDBO extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;
    private Double price;
    private String imageUrl;
    private RealmList<ImageDBO> images = new RealmList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ImageDBO> getImages() {
        return images;
    }

    public void setImages(RealmList<ImageDBO> images) {
        this.images = images;
    }
}
