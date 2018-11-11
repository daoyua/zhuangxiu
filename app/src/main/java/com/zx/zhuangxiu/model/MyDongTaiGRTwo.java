package com.zx.zhuangxiu.model;

public class MyDongTaiGRTwo {
    private String orderId;//订单ID
    private  int payNum;//购买数量
    private int pkId;//主键ID
    private float price;//物品总价
    private int recordId;//订单物品ID
    private String recordName;//物品名称
    private String recordPhoto;//图片地址
    private float recordPrice;//物品单价

    @Override
    public String toString() {
        return "MyDongTaiGRTwo{" +
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

    public float getPrice() {
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
