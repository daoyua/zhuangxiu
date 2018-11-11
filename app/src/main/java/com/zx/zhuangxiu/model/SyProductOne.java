package com.zx.zhuangxiu.model;

import java.util.List;

/**
 * 首页找产品---直接进入
 * */
public class SyProductOne {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"address":"123123","price":500,"cdname":"旧沙发","simple":"旧沙发二次利用","id":5,"IsThumbsUp":0,"picture":"/upload/20180918/e5668201-e93b-4c72-b746-03c2b289859f.jpg","upnum":2},{"address":"123123","price":10,"cdname":"一次性毛巾","simple":"一次性毛巾为了您的卫生，我们会更好的服务每一个人！","id":4,"IsThumbsUp":0,"picture":"/upload/20180918/f6948e6f-b997-460d-98f2-0b2ad6e82f1f.jpg","upnum":2},{"address":"123123","price":5000,"cdname":"钢筋辐条","simple":"钢筋辐条用于建筑使用","id":7,"IsThumbsUp":0,"picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png","upnum":1},{"address":"123123","price":50,"cdname":"洗洁精","simple":"洗洁精让家更洁净！","id":6,"IsThumbsUp":0,"picture":"/upload/20180918/5d943e74-5954-473c-8c44-066b8d5e8651.jpg","upnum":0},{"address":"123123","price":102,"cdname":"大理石","simple":"大理石只要是用于地面的铺设","id":3,"IsThumbsUp":0,"picture":"/upload/20180918/04163971-b9fd-4daa-8c33-121cfc3b2482.jpg","upnum":0},{"address":"123123","price":11,"cdname":"女王范\u2014\u2014项目装修","simple":"212121212","id":2,"IsThumbsUp":0,"picture":"/upload/20180829/b341f731-06bd-425a-9f9e-64c2ff36b8fc.png"},{"address":"123123","price":123,"cdname":"阳台小公园","simple":"Asdasd","id":1,"IsThumbsUp":0,"picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"}]
     * totalCount : 7
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
         * IsThumbsUp : 0
         * picture : /upload/20180918/e5668201-e93b-4c72-b746-03c2b289859f.jpg
         * upnum : 2
         */

        private String address;
        private double price;
        private String cdname;
        private String simple;
        private int id;
        private int IsThumbsUp;
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

        public void setPrice(double price) {
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

        public int getIsThumbsUp() {
            return IsThumbsUp;
        }

        public void setIsThumbsUp(int IsThumbsUp) {
            this.IsThumbsUp = IsThumbsUp;
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
