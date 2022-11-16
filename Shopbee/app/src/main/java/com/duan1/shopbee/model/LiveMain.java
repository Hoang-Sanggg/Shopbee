package com.duan1.shopbee.model;

import java.io.Serializable;

public class LiveMain implements Serializable {
    private String imgUser;
    private String imgLiving;
    private String UserName;
    private String Description;

    public LiveMain(String imgUser, String imgLiving, String userName, String description) {
        this.imgUser = imgUser;
        this.imgLiving = imgLiving;
        UserName = userName;
        Description = description;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getImgLiving() {
        return imgLiving;
    }

    public void setImgLiving(String imgLiving) {
        this.imgLiving = imgLiving;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
