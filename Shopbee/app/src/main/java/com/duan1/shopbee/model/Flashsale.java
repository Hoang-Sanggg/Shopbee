package com.duan1.shopbee.model;

import java.io.Serializable;

public class Flashsale implements Serializable {
    private String imageFlashSale;
    private String priceFlashSale;
    private String discountFlashSale;
    private String soldFlashSale;

    public Flashsale() {
    }

    public Flashsale(String imageFlashSale, String priceFlashSale, String discountFlashSale, String soldFlashSale) {
        this.imageFlashSale = imageFlashSale;
        this.priceFlashSale = priceFlashSale;
        this.discountFlashSale = discountFlashSale;
        this.soldFlashSale = soldFlashSale;
    }

    public String getImageFlashSale() {
        return imageFlashSale;
    }

    public void setImageFlashSale(String imageFlashSale) {
        this.imageFlashSale = imageFlashSale;
    }

    public String getPriceFlashSale() {
        return priceFlashSale;
    }

    public void setPriceFlashSale(String priceFlashSale) {
        this.priceFlashSale = priceFlashSale;
    }

    public String getDiscountFlashSale() {
        return discountFlashSale;
    }

    public void setDiscountFlashSale(String discountFlashSale) {
        this.discountFlashSale = discountFlashSale;
    }

    public String getSoldFlashSale() {
        return soldFlashSale;
    }

    public void setSoldFlashSale(String soldFlashSale) {
        this.soldFlashSale = soldFlashSale;
    }
}
