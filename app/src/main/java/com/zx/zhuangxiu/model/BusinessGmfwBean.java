package com.zx.zhuangxiu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusinessGmfwBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":1,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":2,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":3,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":4,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":5,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":6,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"},{"area":88.88,"address":"2","addTime":1534902363000,"isalone":0,"telenumber":"12312312312","userId":1,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","isgood":0,"price":0.02,"serverType":6,"name":"2","startTime":1533830400000,"id":7,"endTime":1536508800000,"thumbsUp":0,"requires":"暂无需求"}]
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
         * area : 88.88
         * address : 2
         * addTime : 1534902363000
         * isalone : 0
         * telenumber : 12312312312
         * userId : 1
         * imgUrl : /upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg
         * isgood : 0
         * price : 0.02
         * serverType : 6
         * name : 2
         * startTime : 1533830400000
         * id : 1
         * endTime : 1536508800000
         * thumbsUp : 0
         * requires : 暂无需求
         */

        private double area;
        private String address;
        private long addTime;
        private int isalone;
        private String telenumber;
        private int userId;
        private String imgUrl;
        private int isgood;
        private double price;
        private int serverType;
        private String name;
        private long startTime;
        private int id;
        private long endTime;
        private int thumbsUp;
        private String requires;
        @SerializedName("phone")
        public String phone;
        public String distance;

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

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

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public int getIsalone() {
            return isalone;
        }

        public void setIsalone(int isalone) {
            this.isalone = isalone;
        }

        public String getTelenumber() {
            return telenumber;
        }

        public void setTelenumber(String telenumber) {
            this.telenumber = telenumber;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIsgood() {
            return isgood;
        }

        public void setIsgood(int isgood) {
            this.isgood = isgood;
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

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getThumbsUp() {
            return thumbsUp;
        }

        public void setThumbsUp(int thumbsUp) {
            this.thumbsUp = thumbsUp;
        }

        public String getRequires() {
            return requires;
        }

        public void setRequires(String requires) {
            this.requires = requires;
        }
    }
}
