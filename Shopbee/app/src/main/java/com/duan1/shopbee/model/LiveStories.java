package com.duan1.shopbee.model;

import java.io.Serializable;

public class LiveStories implements Serializable {

    private String Image;
    private String Name;



    public LiveStories(String image, String name) {
        Image = image;
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
