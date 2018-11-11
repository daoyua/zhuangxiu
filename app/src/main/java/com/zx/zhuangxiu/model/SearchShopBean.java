package com.zx.zhuangxiu.model;

import java.util.List;

public class SearchShopBean {

    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"address":"123123","price":500,"cdname":"旧沙发","simple":"旧沙发二次利用","id":5,"picture":"/upload/20180918/e5668201-e93b-4c72-b746-03c2b289859f.jpg","upnum":27}]
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
         * address : 123123
         * price : 500
         * cdname : 旧沙发
         * simple : 旧沙发二次利用
         * id : 5
         * picture : /upload/20180918/e5668201-e93b-4c72-b746-03c2b289859f.jpg
         * upnum : 27
         */

        private String address;
        private double price;
        private String cdname;
        private String simple;
        private int id;
        private String picture;
        private int upnum;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getUpnum() {
            return upnum;
        }

        public void setUpnum(int upnum) {
            this.upnum = upnum;
        }
    }
}
