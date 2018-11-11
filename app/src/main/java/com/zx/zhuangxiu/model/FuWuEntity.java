package com.zx.zhuangxiu.model;

public class FuWuEntity {

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"imgUrl":"/upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg","address":"2","addTime":"2018-09-26 14:35:40","isalone":1,"isgood":1,"name":"大时圈","nickname":"admin","id":10,"thumbsup":8,"userUrl":"/manage/login/img/headurl.png","requires":"暂无需求"}
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
         * imgUrl : /upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg
         * address : 2
         * addTime : 2018-09-26 14:35:40
         * isalone : 1
         * isgood : 1
         * name : 大时圈
         * nickname : admin
         * id : 10
         * thumbsup : 8
         * userUrl : /manage/login/img/headurl.png
         * requires : 暂无需求
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
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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
