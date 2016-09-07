package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDVO {

    private List<CategoryDVO> categories = new ArrayList<>();

    public CategoryListDVO(List<CategoryDVO> categories) {
        this.categories = categories;
    }

    public CategoryListDVO() {
    }

    public List<CategoryDVO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDVO> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CategoryListDVO{" +
                "categories=" + categories +
                '}';
    }
}
