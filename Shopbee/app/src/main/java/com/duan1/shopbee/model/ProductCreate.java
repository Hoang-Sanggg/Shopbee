package com.duan1.shopbee.model;

import java.io.Serializable;

public class ProductCreate implements Serializable {

    private String idProduct;
    private String nameProduct;
    private String description; // mo ta
    private String industry; // nganh hang
    private String priceProduct; // gia
    private String productdetail; // chi tiet san pham
    private String warehouse; // ton kho
    private String transportfee; // phi van chuyen
    private String status; // trang thai
    private String nameShop;
    private String soldProduct; // da ban
    private String brandProduct;
    private String originProduct; // xuat xu
    private String baoHanhSp;
    private String shippingProduct;
    private String priceFlashSale;
    private String discountFlashSale;
    private String soldFlashSale;
    private String imageProduct;

    public ProductCreate(){}

    public ProductCreate(String name, String description, String industry, String productdetail, String warehouse, String transportfee, String status) {
        this.nameProduct = name;
        this.description = description;
        this.industry = industry;
        this.productdetail = productdetail;
        this.warehouse = warehouse;
        this.transportfee = transportfee;
        this.status = status;
    }

    public ProductCreate(String idProduct, String nameProduct, String description, String industry, String priceProduct, String productdetail, String warehouse, String transportfee, String status, String nameShop, String soldProduct, String brandProduct, String originProduct, String baoHanhSp, String shippingProduct, String priceFlashSale, String discountFlashSale, String soldFlashSale, String imageProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.industry = industry;
        this.priceProduct = priceProduct;
        this.productdetail = productdetail;
        this.warehouse = warehouse;
        this.transportfee = transportfee;
        this.status = status;
        this.nameShop = nameShop;
        this.soldProduct = soldProduct;
        this.brandProduct = brandProduct;
        this.originProduct = originProduct;
        this.baoHanhSp = baoHanhSp;
        this.shippingProduct = shippingProduct;
        this.priceFlashSale = priceFlashSale;
        this.discountFlashSale = discountFlashSale;
        this.soldFlashSale = soldFlashSale;
        this.imageProduct = imageProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getSoldProduct() {
        return soldProduct;
    }

    public void setSoldProduct(String soldProduct) {
        this.soldProduct = soldProduct;
    }

    public String getBrandProduct() {
        return brandProduct;
    }

    public void setBrandProduct(String brandProduct) {
        this.brandProduct = brandProduct;
    }

    public String getOriginProduct() {
        return originProduct;
    }

    public void setOriginProduct(String originProduct) {
        this.originProduct = originProduct;
    }

    public String getBaoHanhSp() {
        return baoHanhSp;
    }

    public void setBaoHanhSp(String baoHanhSp) {
        this.baoHanhSp = baoHanhSp;
    }

    public String getShippingProduct() {
        return shippingProduct;
    }

    public void setShippingProduct(String shippingProduct) {
        this.shippingProduct = shippingProduct;
    }

    public String getName() {
        return nameProduct;
    }

    public void setName(String name) {
        this.nameProduct = name;
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
