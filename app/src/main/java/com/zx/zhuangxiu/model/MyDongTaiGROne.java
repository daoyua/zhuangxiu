package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDongTaiGROne {
    private String addressId;//收货地址ID
    private long createTime;//订单创建时间
    private int payNum;//购买数量
    private String payType;//支付方式
    private String pkId;//主键ID
    private float price;//订单总价格
    private String status;//订单状态0:未支付；1:已支付；2:已取消，3:已删除
    private String type;//类别 1：服务，2： 产品
    private long updateTime;//修改时间
    private int userId;//用户ID
    private List<MyDongTaiGRTwo>jskPersonOrderDetail;//详情list
    private String express;
    private String expressType;

    @Override
    public String toString() {
        return "MyDongTaiGROne{" +
                "addressId='" + addressId + '\'' +
                ", createTime=" + createTime +
                ", payNum=" + payNum +
                ", payType='" + payType + '\'' +
                ", pkId='" + pkId + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                ", jskPersonOrderDetail=" + jskPersonOrderDetail +
                ", express='" + express + '\'' +
                ", expressType='" + expressType + '\'' +
                '}';
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

    public float getPrice() {
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

    public List<MyDongTaiGRTwo> getJskPersonOrderDetail() {
        return jskPersonOrderDetail;
    }

    public void setJskPersonOrderDetail(List<MyDongTaiGRTwo> jskPersonOrderDetail) {
        this.jskPersonOrderDetail = jskPersonOrderDetail;
    }
}
