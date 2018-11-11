package com.zx.zhuangxiu.model;

import java.util.List;

public class JiFenShangChengOne {
    private List<JiFenShangChengTwo> jskSysAnnex;//图片地址
    private String title;//标题
    private String summary;//简介
    private int saleNum;//已卖
    private int totalNum;//总数
    private String content;//内容
    private double price;//积分
    private int pkId;

    @Override
    public String toString() {
        return "JiFenShangChengOne{" +
                "jskSysAnnex=" + jskSysAnnex +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", saleNum=" + saleNum +
                ", totalNum=" + totalNum +
                ", content='" + content + '\'' +
                ", price=" + price +
                '}';
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public List<JiFenShangChengTwo> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<JiFenShangChengTwo> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
