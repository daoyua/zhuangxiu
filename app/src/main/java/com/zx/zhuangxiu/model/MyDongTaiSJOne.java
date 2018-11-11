package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDongTaiSJOne {
    private String title;
    private String summary;
    private int totalNum;
    private String status;
    private float price;
    private List<MyDongTaiSJTwo>jskSysAnnex;

    @Override
    public String toString() {
        return "MyDongTaiSJOne{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", totalNum=" + totalNum +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", jskSysAnnex=" + jskSysAnnex +
                '}';
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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<MyDongTaiSJTwo> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<MyDongTaiSJTwo> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }
}
