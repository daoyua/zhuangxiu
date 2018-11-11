package com.zx.zhuangxiu.model;

public class FuwuxiangqingBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"imgUrl":"/upload/20180906/22158e2a-5d6a-469e-a576-8ec17e654303.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":1,"isgood":0,"name":"2","nickname":"admin","id":2,"thumbsup":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"}
     */

    private int result;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl : /upload/20180906/22158e2a-5d6a-469e-a576-8ec17e654303.jpg
         * address : 2
         * addTime : 2018-08-22 09:46:03
         * isalone : 1
         * isgood : 0
         * name : 2
         * nickname : admin
         * id : 2
         * thumbsup : 0
         * userUrl : /upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png
         * requires : 暂无需求
         * price : 0.02
         */

        private String imgUrl;
        private String address;
        private String addTime;
        private int isalone;
        private int isgood;
        private String name;
        private String nickname;
        private int id;
        private int thumbsup;
        private String userUrl;
        private String requires;
        private double price;
        private String telenumber;
        private int userId;
        private double area;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        public String getTelenumber() {
            return telenumber;
        }

        public void setTelenumber(String telenumber) {
            this.telenumber = telenumber;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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

        public int getIsalone() {
            return isalone;
        }

        public void setIsalone(int isalone) {
            this.isalone = isalone;
        }

        public int getIsgood() {
            return isgood;
        }

        public void setIsgood(int isgood) {
            this.isgood = isgood;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getThumbsup() {
            return thumbsup;
        }

        public void setThumbsup(int thumbsup) {
            this.thumbsup = thumbsup;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getRequires() {
            return requires;
        }

        public void setRequires(String requires) {
            this.requires = requires;
        }
    }
}
