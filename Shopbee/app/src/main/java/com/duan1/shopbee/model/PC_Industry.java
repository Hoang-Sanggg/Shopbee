package com.duan1.shopbee.model;

import java.io.Serializable;

public class PC_Industry implements Serializable {
    private String name;

    public PC_Industry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
