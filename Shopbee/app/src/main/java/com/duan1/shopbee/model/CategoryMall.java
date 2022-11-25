package com.duan1.shopbee.model;

import java.util.List;

public class CategoryMall {

    private String nameCategory;
    private String imageCategory;

    public CategoryMall(String nameCategory, String imageCategory) {
        this.nameCategory = nameCategory;
        this.imageCategory = imageCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }
}
