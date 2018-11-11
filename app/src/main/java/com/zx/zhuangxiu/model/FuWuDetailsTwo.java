package com.zx.zhuangxiu.model;

import java.util.List;

public class FuWuDetailsTwo {

    private int area;
    private int commentNum;
    private int createId;
    private int photo;
    private int pkId;
    private int praiseNum;
    private int updateId;
    private int workId;
    private String address;
    private String content;
    private String remark;
    private String status;
    private String summary;
    private String title;
    private double price;
    private List<FuWuDetailsThree> jskSysAnnex;
    private FuWuDetailsFour jskSysUser;

    public FuWuDetailsFour getJskSysUser() {
        return jskSysUser;
    }

    public void setJskSysUser(FuWuDetailsFour jskSysUser) {
        this.jskSysUser = jskSysUser;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<FuWuDetailsThree> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<FuWuDetailsThree> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }
}
