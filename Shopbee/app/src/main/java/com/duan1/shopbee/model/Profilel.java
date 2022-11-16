package com.duan1.shopbee.model;

import java.io.Serializable;

public class Profilel implements Serializable {
    private String nameProfile;
    private String imgProfile;

    public Profilel() {
    }

    public Profilel(String nameProfile, String imgProfile) {
        this.nameProfile = nameProfile;
        this.imgProfile = imgProfile;
    }

    public String getNameProfile() {
        return nameProfile;
    }

    public void setNameProfile(String nameProfile) {
        this.nameProfile = nameProfile;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }
}
