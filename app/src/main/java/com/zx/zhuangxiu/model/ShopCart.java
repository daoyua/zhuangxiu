package com.zx.zhuangxiu.model;

import java.io.Serializable;

public class ShopCart implements Serializable {
    private int proImg;
    private String ProName;
    private String shopPrice;
    private String markPrice;
    private String proCount;
    private String image;
    private int proInt;
    private double proPrice;

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    public int getProInt() {
        return proInt;
    }

    public void setProInt(int proInt) {
        this.proInt = proInt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProImg() {
        return proImg;
    }

    public void setProImg(int proImg) {
        this.proImg = proImg;
    }

    public String getProName() {
        return ProName;
    }

    public void setProName(String proName) {
        ProName = proName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(String markPrice) {
        this.markPrice = markPrice;
    }

    public String getProCount() {
        return proCount;
    }

    public void setProCount(String proCount) {
        this.proCount = proCount;
    }
}
