package com.duan1.shopbee.model;

import android.widget.ImageView;

import java.io.Serializable;

public class LiveVoucher implements Serializable {

    private String VoucherImage;
    private String Name;

    public LiveVoucher(String voucherImage, String name) {
        VoucherImage = voucherImage;
        Name = name;
    }

    public String getVoucherImage() {
        return VoucherImage;
    }

    public void setVoucherImage(String voucherImage) {
        VoucherImage = voucherImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
