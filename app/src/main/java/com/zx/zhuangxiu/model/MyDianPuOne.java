package com.zx.zhuangxiu.model;

public class MyDianPuOne {
        private int head_photo;
        private String address;
        private String company_name;
        private String hesdFilePath;

    @Override
    public String toString() {
        return "MyDianPuOne{" +
                "head_photo=" + head_photo +
                ", address='" + address + '\'' +
                ", company_name='" + company_name + '\'' +
                ", hesdFilePath='" + hesdFilePath + '\'' +
                '}';
    }

    public int getHead_photo() {
        return head_photo;
    }

    public void setHead_photo(int head_photo) {
        this.head_photo = head_photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getHesdFilePath() {
        return hesdFilePath;
    }

    public void setHesdFilePath(String hesdFilePath) {
        this.hesdFilePath = hesdFilePath;
    }
}
