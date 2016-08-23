package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class CategoryDVO {

    private int id;
    private String name;
    private String imageUrl;
    private List<SubcategoryDVO> subcategories = new ArrayList<>();

    public CategoryDVO(int id, String name, String imageUrl, List<SubcategoryDVO> subcategories) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.subcategories = subcategories;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<SubcategoryDVO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryDVO> subcategories) {
        this.subcategories = subcategories;
    }
}
