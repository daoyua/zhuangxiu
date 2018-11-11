package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDongTaiQYOne {
    private String address;//工作地址
    private float area;//面积
    private int commentNum;//评论数
    private String content;//内容
    private int createId;
    private long createTime;
    private long endDate;//结束时间
    private List<MyDongTaiQYTwo>jskSysAnnex;
    private MyDongTaiQYThree jskSysUser;
    private int photo;//图片附件表关联
    private int pkId;//主键
    private int praiseNum;//点赞数
    private float price;//价格
    private String remark;//备注
    private String service_type;//发布类型：1：服务2：工作
    private long startDate;//开始时间
    private String status;//状态1，发布2，未发布
    private String summary;//简介
    private String title;//标题
    private String type;//发布内容类型
    private int updateId;
    private long updateTime;
    private int workId;

    @Override
    public String toString() {
        return "MyDongTaiQYOne{" +
                "address='" + address + '\'' +
                ", area=" + area +
                ", commentNum=" + commentNum +
                ", content='" + content + '\'' +
                ", createId=" + createId +
                ", createTime=" + createTime +
                ", endDate=" + endDate +
                ", jskSysAnnex=" + jskSysAnnex +
                ", jskSysUser=" + jskSysUser +
                ", photo=" + photo +
                ", pkId=" + pkId +
                ", praiseNum=" + praiseNum +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", service_type='" + service_type + '\'' +
                ", startDate=" + startDate +
                ", status='" + status + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", updateId=" + updateId +
                ", updateTime=" + updateTime +
                ", workId=" + workId +
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public List<MyDongTaiQYTwo> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<MyDongTaiQYTwo> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }

    public MyDongTaiQYThree getJskSysUser() {
        return jskSysUser;
    }

    public void setJskSysUser(MyDongTaiQYThree jskSysUser) {
        this.jskSysUser = jskSysUser;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }
}
