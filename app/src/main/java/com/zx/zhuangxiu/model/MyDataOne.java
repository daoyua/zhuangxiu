package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDataOne {
    private String nickName;//昵称
    private String mobile;//电话
    private String balance;//余额
    private double integral;//积分
    private List<MyDataTwo>jskSysAnnex;
    private String type;//1个人，2企业，3店铺商家



    @Override
    public String toString() {
        return "MyDataOne{" +
                "nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", balance='" + balance + '\'' +
                ", integral=" + integral +
                ", jskSysAnnex=" + jskSysAnnex +
                ", type='" + type + '\'' +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public List<MyDataTwo> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<MyDataTwo> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
