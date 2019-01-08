package com.zx.zhuangxiu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 新添加的实体类
 *
 */
public class SyFuWuss {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"tatalPage":1,"count":5,"page":[{"imgUrl":"/upload/20180906/c9010f84-8426-4b8a-9f39-3ffa3e80aad7.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":0,"isgood":1,"name":"2","id":1,"thumbsup":9,"requires":"能独立完成的工作"},{"imgUrl":"/upload/20180906/22158e2a-5d6a-469e-a576-8ec17e654303.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":1,"isgood":0,"name":"2","id":2,"thumbsup":6,"requires":"暂无需求"},{"imgUrl":"/upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":1,"isgood":1,"name":"2","id":4,"thumbsup":8,"requires":"暂无需求"},{"imgUrl":"/upload/20180926/bd1d2da5-12f1-426a-bb33-c67a542245a3.jpg","address":"北京西站","addTime":"2018-09-26 14:35:32","isalone":0,"isgood":0,"name":"大时圈","id":11,"thumbsup":1,"requires":"主要是泥工，瓦工"},{"imgUrl":"/upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg","address":"2","addTime":"2018-09-26 14:35:40","isalone":1,"isgood":1,"name":"大时圈","id":10,"thumbsup":9,"requires":"暂无需求"}],"classList":[{"cname":"家居布艺","id":16},{"cname":"办公生活","id":18},{"cname":"灯具灯饰","id":19}]}
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
         * tatalPage : 1
         * count : 5
         * page : [{"imgUrl":"/upload/20180906/c9010f84-8426-4b8a-9f39-3ffa3e80aad7.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":0,"isgood":1,"name":"2","id":1,"thumbsup":9,"requires":"能独立完成的工作"},{"imgUrl":"/upload/20180906/22158e2a-5d6a-469e-a576-8ec17e654303.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":1,"isgood":0,"name":"2","id":2,"thumbsup":6,"requires":"暂无需求"},{"imgUrl":"/upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg","address":"2","addTime":"2018-08-22 09:46:03","isalone":1,"isgood":1,"name":"2","id":4,"thumbsup":8,"requires":"暂无需求"},{"imgUrl":"/upload/20180926/bd1d2da5-12f1-426a-bb33-c67a542245a3.jpg","address":"北京西站","addTime":"2018-09-26 14:35:32","isalone":0,"isgood":0,"name":"大时圈","id":11,"thumbsup":1,"requires":"主要是泥工，瓦工"},{"imgUrl":"/upload/20180906/9e567316-002b-45a0-b687-8528495b199f.jpg","address":"2","addTime":"2018-09-26 14:35:40","isalone":1,"isgood":1,"name":"大时圈","id":10,"thumbsup":9,"requires":"暂无需求"}]
         * classList : [{"cname":"家居布艺","id":16},{"cname":"办公生活","id":18},{"cname":"灯具灯饰","id":19}]
         */

        private int tatalPage;
        private int count;
        private List<PageBean> page;
        private List<ClassListBean> classList;

        public int getTatalPage() {
            return tatalPage;
        }

        public void setTatalPage(int tatalPage) {
            this.tatalPage = tatalPage;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<PageBean> getPage() {
            return page;
        }

        public void setPage(List<PageBean> page) {
            this.page = page;
        }

        public List<ClassListBean> getClassList() {
            return classList;
        }

        public void setClassList(List<ClassListBean> classList) {
            this.classList = classList;
        }

        public static class PageBean {
            /**
             * imgUrl : /upload/20180906/c9010f84-8426-4b8a-9f39-3ffa3e80aad7.jpg
             * address : 2
             * addTime : 2018-08-22 09:46:03
             * isalone : 0
             * isgood : 1
             * name : 2
             * id : 1
             * thumbsup : 9
             * requires : 能独立完成的工作
             */

            private String imgUrl;
            private String address;
            private int isalone;
            private int isgood;
            private String name;
            private int id;
            private int thumbsup;
            private String requires;
            private String distance;

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            @SerializedName("addTime")
            public String addTime;

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

        public static class ClassListBean {
            /**
             * cname : 家居布艺
             * id : 16
             */

            private String cname;
            private int id;

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
