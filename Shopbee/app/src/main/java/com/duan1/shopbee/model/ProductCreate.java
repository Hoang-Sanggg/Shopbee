package com.duan1.shopbee.model;

import java.io.Serializable;

public class ProductCreate implements Serializable {

    private String name;
    private String description;
    private String industry;
    private String productdetail;
    private String warehouse;
    private String transportfee;
    private String status;
    public ProductCreate(){}

    public ProductCreate(String name, String description, String industry, String productdetail, String warehouse, String transportfee, String status) {
        this.name = name;
        this.description = description;
        this.industry = industry;
        this.productdetail = productdetail;
        this.warehouse = warehouse;
        this.transportfee = transportfee;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(String productdetail) {
        this.productdetail = productdetail;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getTransportfee() {
        return transportfee;
    }

    public void setTransportfee(String transportfee) {
        this.transportfee = transportfee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
