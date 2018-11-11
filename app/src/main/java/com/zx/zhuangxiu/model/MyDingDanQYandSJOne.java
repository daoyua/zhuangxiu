package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanQYandSJOne {
    private String addressId;
    private long createTime;
    private String express;
    private String expressType;
    private List<MyDingDanQYandSJTwo>jskPersonAddress;
    private List<MyDingDanQYandSJThree>jskPersonOrderDetail;
    private int payNum;
    private String payType;
    private String pkId;
    private double price;
    private String status;
    private String type;
    private long updateTime;
    private int userId;

    @Override
    public String toString() {
        return "MyDingDanQYandSJOne{" +
                "addressId='" + addressId + '\'' +
                ", createTime=" + createTime +
                ", express='" + express + '\'' +
                ", expressType='" + expressType + '\'' +
                ", jskPersonAddress=" + jskPersonAddress +
                ", jskPersonOrderDetail=" + jskPersonOrderDetail +
                ", payNum=" + payNum +
                ", payType='" + payType + '\'' +
                ", pkId='" + pkId + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                '}';
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public List<MyDingDanQYandSJTwo> getJskPersonAddress() {
        return jskPersonAddress;
    }

    public void setJskPersonAddress(List<MyDingDanQYandSJTwo> jskPersonAddress) {
        this.jskPersonAddress = jskPersonAddress;
    }

    public List<MyDingDanQYandSJThree> getJskPersonOrderDetail() {
        return jskPersonOrderDetail;
    }

    public void setJskPersonOrderDetail(List<MyDingDanQYandSJThree> jskPersonOrderDetail) {
        this.jskPersonOrderDetail = jskPersonOrderDetail;
    }

    public int getPayNum() {
        return payNum;
    }

    public void setPayNum(int payNum) {
        this.payNum = payNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
