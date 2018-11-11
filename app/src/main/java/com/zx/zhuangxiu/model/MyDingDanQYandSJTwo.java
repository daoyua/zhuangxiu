package com.zx.zhuangxiu.model;

public class MyDingDanQYandSJTwo {
    private String address;
    private String mobile;
    private String userName;

    @Override
    public String toString() {
        return "MyDingDanQYandSJTwo{" +
                "address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
