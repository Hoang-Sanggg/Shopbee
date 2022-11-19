package com.duan1.shopbee.model;

import java.io.Serializable;

public class Profile implements Serializable {

    private String Pimg;
    private String Pname;


    public Profile(String pimg, String pname) {
        Pimg = pimg;
        Pname = pname;
    }

    public String getPimg() {
        return Pimg;
    }

    public void setPimg(String pimg) {
        Pimg = pimg;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }
}
