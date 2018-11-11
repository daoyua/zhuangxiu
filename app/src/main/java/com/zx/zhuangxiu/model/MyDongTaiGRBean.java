package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDongTaiGRBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"area":120,"imgUrl":"/upload/20180829/32816efe-813a-4472-8923-099c906c6b53.png","address":"中储广场","addTime":"2018-08-29 14:16:50","serverType":10,"name":"金牌服务","startTime":"2018-08-24 00:00:00","endTime":"2018-09-14 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"没有"},{"area":120,"imgUrl":"/upload/20180829/d3e4a0e6-fb87-499b-afb7-185ccc1195bb.png","address":"中储广场","addTime":"2018-08-29 14:17:52","serverType":10,"name":"金牌项目","startTime":"2018-08-24 00:00:00","endTime":"2018-09-14 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"没有"},{"area":145,"imgUrl":"/upload/20180829/67373725-d2a2-4dc4-af9c-2bd5c472b7f2.jpg","address":"中湖广场","addTime":"2018-08-29 14:18:34","serverType":10,"name":"私人专属","startTime":"2018-08-23 00:00:00","endTime":"2018-09-06 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"14"},{"area":212,"imgUrl":"/upload/20180829/709f3b78-e3f1-468e-b939-1b1edf6c7784.png","address":"石家庄","addTime":"2018-08-29 17:39:41","serverType":7,"name":"寻找专业设计团队","startTime":"2018-09-06 00:00:00","endTime":"2018-09-28 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"没有"}]
     * totalCount : 4
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
         * area : 120
         * imgUrl : /upload/20180829/32816efe-813a-4472-8923-099c906c6b53.png
         * address : 中储广场
         * addTime : 2018-08-29 14:16:50
         * serverType : 10
         * name : 金牌服务
         * startTime : 2018-08-24 00:00:00
         * endTime : 2018-09-14 00:00:00
         * userName : admin
         * thumbsUp : 0
         * userUrl : /upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png
         * requires : 没有
         */

        private int area;
        private String imgUrl;
        private String address;
        private String addTime;
        private int serverType;
        private String name;
        private String startTime;
        private String endTime;
        private String userName;
        private int thumbsUp;
        private String userUrl;
        private String requires;

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getThumbsUp() {
            return thumbsUp;
        }

        public void setThumbsUp(int thumbsUp) {
            this.thumbsUp = thumbsUp;
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
