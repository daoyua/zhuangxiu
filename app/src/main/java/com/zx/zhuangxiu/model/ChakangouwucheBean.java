package com.zx.zhuangxiu.model;

import java.util.List;

public class ChakangouwucheBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"pin":0,"price":39.9,"count":1,"cdname":"阳台小公园","shopCartId":1,"simple":"Asdasd","userId":"1","picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"}]
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
         * price : 39.9
         * count : 1
         * cdname : 阳台小公园
         * shopCartId : 1
         * simple : Asdasd
         * userId : 1
         * picture : /upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png
         */

        private int pin;
        private double price;
        private int count;
        private String cdname;
        private int shopCartId;
        private String simple;
        private String userId;
        private String picture;
        private boolean isChoosed;

        public int getPin() {
            return pin;
        }

        public void setPin(int pin) {
            this.pin = pin;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getCdname() {
            return cdname;
        }

        public void setCdname(String cdname) {
            this.cdname = cdname;
        }

        public int getShopCartId() {
            return shopCartId;
        }

        public void setShopCartId(int shopCartId) {
            this.shopCartId = shopCartId;
        }

        public String getSimple() {
            return simple;
        }

        public void setSimple(String simple) {
            this.simple = simple;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
        public boolean getChoosed() {
            return isChoosed;
        }

        public void setChoosed(boolean choosed) {
            isChoosed = choosed;
        }
    }
}
