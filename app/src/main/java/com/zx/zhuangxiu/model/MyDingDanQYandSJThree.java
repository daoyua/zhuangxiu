package com.zx.zhuangxiu.model;

public class MyDingDanQYandSJThree {
    private String orderId;
    private int payNum;
    private int pkId;
    private double price;
    private int recordId;
    private String recordName;
    private String recordPhoto;
    private float recordPrice;

    @Override
    public String toString() {
        return "MyDingDanQYandSJThree{" +
                "orderId='" + orderId + '\'' +
                ", payNum=" + payNum +
                ", pkId=" + pkId +
                ", price=" + price +
                ", recordId=" + recordId +
                ", recordName='" + recordName + '\'' +
                ", recordPhoto='" + recordPhoto + '\'' +
                ", recordPrice=" + recordPrice +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPayNum() {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordPhoto() {
        return recordPhoto;
    }

    public void setRecordPhoto(String recordPhoto) {
        this.recordPhoto = recordPhoto;
    }

    public float getRecordPrice() {
        return recordPrice;
    }

    public void setRecordPrice(float recordPrice) {
        this.recordPrice = recordPrice;
    }
}
