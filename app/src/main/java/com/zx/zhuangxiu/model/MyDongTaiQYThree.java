package com.zx.zhuangxiu.model;

public class MyDongTaiQYThree {
    private String address;//发布人注册是的地址
    private long createTime;//注册时间

    @Override
    public String toString() {
        return "MyDongTaiQYThree{" +
                "address='" + address + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
