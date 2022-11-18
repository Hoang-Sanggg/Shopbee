package com.duan1.shopbee.model;

import java.io.Serializable;

public class Category implements Serializable {
    private String nameCategory;
    private String imageCategory;

    public Category() {
    }

    public Category(String nameCategory, String imageCategory) {
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
