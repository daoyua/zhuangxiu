package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanGRXQFuWuXingQingTwo {
    private String address;
    private float area;
    private long endDate;
    private List<MyDingDanGRXQFuWuXingQingThree>jskSysUser;
    private int praiseNum;
    private String remark;
    private long startDate;
    private String title;

    @Override
    public String toString() {
        return "MyDingDanGRXQFuWuXingQingTwo{" +
                "address='" + address + '\'' +
                ", area=" + area +
                ", endDate=" + endDate +
                ", jskSysUser=" + jskSysUser +
                ", praiseNum=" + praiseNum +
                ", remark='" + remark + '\'' +
                ", startDate=" + startDate +
                ", title='" + title + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public List<MyDingDanGRXQFuWuXingQingThree> getJskSysUser() {
        return jskSysUser;
    }

    public void setJskSysUser(List<MyDingDanGRXQFuWuXingQingThree> jskSysUser) {
        this.jskSysUser = jskSysUser;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
