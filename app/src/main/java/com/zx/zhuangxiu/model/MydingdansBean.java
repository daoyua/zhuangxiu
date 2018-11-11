package com.zx.zhuangxiu.model;

import java.util.List;

public class MydingdansBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"area":88.88,"address":"2","addTime":"2018-08-23 10:42:59","saddTime":"2018-08-22 09:46:03","imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","price":0.01,"serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","id":6,"endTime":"2018-09-10 00:00:00","requires":"暂无需求","status":"待支付"},{"area":88.88,"address":"2","addTime":"2018-08-23 10:32:12","saddTime":"2018-08-22 09:46:03","imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","price":0.01,"serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","id":6,"endTime":"2018-09-10 00:00:00","requires":"暂无需求","status":"待支付"},{"area":88.88,"address":"2","addTime":"2018-08-23 10:41:40","saddTime":"2018-08-22 09:46:03","imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","price":0.01,"serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","id":6,"endTime":"2018-09-10 00:00:00","requires":"暂无需求","status":"待支付"},{"area":88.88,"address":"2","addTime":"2018-08-23 11:49:24","saddTime":"2018-08-22 09:46:03","imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","price":0.02,"serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","id":7,"endTime":"2018-09-10 00:00:00","requires":"暂无需求","status":"待支付"},{"area":88.88,"address":"2","addTime":"2018-08-23 10:39:43","saddTime":"2018-08-22 09:46:03","imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","price":0.01,"serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","id":6,"endTime":"2018-09-10 00:00:00","requires":"暂无需求","status":"待支付"}]
     * totalCount : 5
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
         * area : 88.88
         * address : 2
         * addTime : 2018-08-23 10:42:59
         * saddTime : 2018-08-22 09:46:03
         * imgUrl : /upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg
         * price : 0.01
         * serverType : 6
         * name : 2
         * startTime : 2018-08-10 00:00:00
         * id : 6
         * endTime : 2018-09-10 00:00:00
         * requires : 暂无需求
         * status : 待支付
         */

        private double area;
        private String address;
        private String addTime;
        private String saddTime;
        private String imgUrl;
        private double price;
        private int serverType;
        private String name;
        private String startTime;
        private int id;
        private String endTime;
        private String requires;
        private String status;

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getSaddTime() {
            return saddTime;
        }

        public void setSaddTime(String saddTime) {
            this.saddTime = saddTime;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getServerType() {
            return serverType;
        }

        public void setServerType(int serverType) {
            this.serverType = serverType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRequires() {
            return requires;
        }

        public void setRequires(String requires) {
            this.requires = requires;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
