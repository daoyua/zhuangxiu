package com.zx.zhuangxiu.model;

import java.util.List;

public class JiFenShangChengBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"address":"123123","pin":0,"price":123,"cdname":"阳台小公园","simple":"Asdasd","id":1,"userName":"admin","userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"},{"address":"123123","pin":0,"price":11,"cdname":"女王范\u2014\u2014项目装修","simple":"212121212","id":2,"userName":"admin","userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","picture":"/upload/20180829/b341f731-06bd-425a-9f9e-64c2ff36b8fc.png"}]
     * totalCount : 2
     */

    private int result;
    private String msg;
    private int totalCount;
    private List<DataBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 123123
         * pin : 0
         * price : 123
         * cdname : 阳台小公园
         * simple : Asdasd
         * id : 1
         * userName : admin
         * userUrl : /upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png
         * picture : /upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png
         */

        private String address;
        private int pin;
        private double price;
        private String cdname;
        private String simple;
        private int id;
        private String userName;
        private String userUrl;
        private String picture;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPin() {
            return pin;
        }

        public void setPin(int pin) {
            this.pin = pin;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCdname() {
            return cdname;
        }

        public void setCdname(String cdname) {
            this.cdname = cdname;
        }

        public String getSimple() {
            return simple;
        }

        public void setSimple(String simple) {
            this.simple = simple;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
