package com.zx.zhuangxiu.model;

import java.util.List;

public class FuWuDianPuBean {

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"shoper":{"address":"北京","nickname":"光子1号","userType":2,"shopUrl":"/upload/20180925/3931392862b84a7a9596d22806a78b8c.png","userId":73},"servcies":[{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:03:54","isalone":0,"isgood":0,"name":"啊","id":15,"thumbsup":0,"requires":"暂无"},{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:10:13","isalone":0,"isgood":0,"name":"啊","id":16,"thumbsup":0,"requires":"暂无"},{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:10:27","isalone":0,"isgood":0,"name":"啊","id":17,"thumbsup":0,"requires":"暂无"}]}
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
         * shoper : {"address":"北京","nickname":"光子1号","userType":2,"shopUrl":"/upload/20180925/3931392862b84a7a9596d22806a78b8c.png","userId":73}
         * servcies : [{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:03:54","isalone":0,"isgood":0,"name":"啊","id":15,"thumbsup":0,"requires":"暂无"},{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:10:13","isalone":0,"isgood":0,"name":"啊","id":16,"thumbsup":0,"requires":"暂无"},{"imgUrl":"/upload/20181023/5a851a31e2f5467a941327424e9c4e98.png","address":"河北石家庄","addTime":"2018-10-23 16:10:27","isalone":0,"isgood":0,"name":"啊","id":17,"thumbsup":0,"requires":"暂无"}]
         */

        private ShoperBean shoper;
        private List<ServciesBean> servcies;

        public ShoperBean getShoper() {
            return shoper;
        }

        public void setShoper(ShoperBean shoper) {
            this.shoper = shoper;
        }

        public List<ServciesBean> getServcies() {
            return servcies;
        }

        public void setServcies(List<ServciesBean> servcies) {
            this.servcies = servcies;
        }

        public static class ShoperBean {
            /**
             * address : 北京
             * nickname : 光子1号
             * userType : 2
             * shopUrl : /upload/20180925/3931392862b84a7a9596d22806a78b8c.png
             * userId : 73
             */

            private String address;
            private String nickname;
            private int userType;
            private String shopUrl;
            private int userId;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getShopUrl() {
                return shopUrl;
            }

            public void setShopUrl(String shopUrl) {
                this.shopUrl = shopUrl;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }

        public static class ServciesBean {
            /**
             * imgUrl : /upload/20181023/5a851a31e2f5467a941327424e9c4e98.png
             * address : 河北石家庄
             * addTime : 2018-10-23 16:03:54
             * isalone : 0
             * isgood : 0
             * name : 啊
             * id : 15
             * thumbsup : 0
             * requires : 暂无
             */

            private String imgUrl;
            private String address;
            private String addTime;
            private int isalone;
            private int isgood;
            private String name;
            private int id;
            private int thumbsup;
            private String requires;

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

            public String getRequires() {
                return requires;
            }

            public void setRequires(String requires) {
                this.requires = requires;
            }
        }
    }
}
