package com.github.handioq.fanshop.model.dbo;

import io.realm.RealmObject;

public class ImageDBO extends RealmObject{

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
