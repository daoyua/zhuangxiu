package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDianPu {

    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"pin":0,"price":5000,"cdname":"钢筋辐条","simple":"钢筋辐条用于建筑使用","id":7,"userId":70,"picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png"}]
     * totalCount : 1
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
         * pin : 0
         * price : 5000
         * cdname : 钢筋辐条
         * simple : 钢筋辐条用于建筑使用
         * id : 7
         * userId : 70
         * picture : /upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png
         */

        private int pin;
        private double price;
        private String cdname;
        private String simple;
        private int id;
        private int userId;
        private String picture;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
