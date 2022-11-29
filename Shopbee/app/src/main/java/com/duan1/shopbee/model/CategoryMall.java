package com.duan1.shopbee.model;

import java.util.List;

public class CategoryMall {

    private String nameCategoryMall;
    private String imageCategoryMall;

    public CategoryMall(String nameCategoryMall, String imageCategoryMall) {
        this.nameCategoryMall = nameCategoryMall;
        this.imageCategoryMall = imageCategoryMall;
    }

    public String getNameCategoryMall() {
        return nameCategoryMall;
    }

    public void setNameCategoryMall(String nameCategoryMall) {
        this.nameCategoryMall = nameCategoryMall;
    }

    public String getImageCategoryMall() {
        return imageCategoryMall;
    }

    public void setImageCategoryMall(String imageCategoryMall) {
        this.imageCategoryMall = imageCategoryMall;
    }
}
