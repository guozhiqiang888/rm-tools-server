package com.farben.readme.bean.config;

import java.io.Serializable;

public class Product implements Serializable {

    private String productName;
    private String link;
    private boolean disabled;
    private String imgUrl;
    private String imgUrl2;
    private String imgUrl3;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", link='" + link + '\'' +
                ", disabled=" + disabled +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgUrl2='" + imgUrl2 + '\'' +
                ", imgUrl3='" + imgUrl3 + '\'' +
                '}';
    }
}
