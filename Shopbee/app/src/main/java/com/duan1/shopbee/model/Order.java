package com.duan1.shopbee.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String idProductOrder;
    private String customer;
    private String seller;
    private String priceOrder;
    private String priceProductOrder;
    private String numberOfOrder;
    private String nameProductOrder;
    private String statusOrder;
    private String dateOrder;
    private String imageOrder;
    private String address;
    private String phone;

    public Order() {
    }

    public Order(String idProductOrder, String customer, String seller, String priceOrder, String priceProductOrder, String numberOfOrder, String nameProductOrder, String statusOrder, String dateOrder, String imageOrder) {
        this.idProductOrder = idProductOrder;
        this.customer = customer;
        this.seller = seller;
        this.priceOrder = priceOrder;
        this.priceProductOrder = priceProductOrder;
        this.numberOfOrder = numberOfOrder;
        this.nameProductOrder = nameProductOrder;
        this.statusOrder = statusOrder;
        this.dateOrder = dateOrder;
        this.imageOrder = imageOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(String imageOrder) {
        this.imageOrder = imageOrder;
    }

    public String getIdProductOrder() {
        return idProductOrder;
    }

    public void setIdProductOrder(String idProductOrder) {
        this.idProductOrder = idProductOrder;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(String priceOrder) {
        this.priceOrder = priceOrder;
    }

    public String getPriceProductOrder() {
        return priceProductOrder;
    }

    public void setPriceProductOrder(String priceProductOrder) {
        this.priceProductOrder = priceProductOrder;
    }

    public String getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(String numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public String getNameProductOrder() {
        return nameProductOrder;
    }

    public void setNameProductOrder(String nameProductOrder) {
        this.nameProductOrder = nameProductOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }
}
