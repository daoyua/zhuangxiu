package com.zx.zhuangxiu.model;

import java.util.List;

public class MyqiyedongtaiBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"},{"area":88.88,"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","addTime":"2018-08-22 09:46:03","serverType":6,"name":"2","startTime":"2018-08-10 00:00:00","endTime":"2018-09-10 00:00:00","userName":"admin","thumbsUp":0,"userUrl":"/upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png","requires":"暂无需求"}]
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
         * imgUrl : /upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg
         * address : 2
         * addTime : 2018-08-22 09:46:03
         * serverType : 6
         * name : 2
         * startTime : 2018-08-10 00:00:00
         * endTime : 2018-09-10 00:00:00
         * userName : admin
         * thumbsUp : 0
         * userUrl : /upload/20180804/07dd4e5a-2a41-4c95-8cfd-66aff0930e8f.png
         * requires : 暂无需求
         */

        private double area;
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

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
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
