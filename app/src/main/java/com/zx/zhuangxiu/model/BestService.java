package com.zx.zhuangxiu.model;

import java.util.List;

public class BestService {

    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"imgUrl":"/upload/20180829/67373725-d2a2-4dc4-af9c-2bd5c472b7f2.jpg","id":13},{"imgUrl":"/upload/20180829/32816efe-813a-4472-8923-099c906c6b53.png","id":11}]
     */

    private int result;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl : /upload/20180829/67373725-d2a2-4dc4-af9c-2bd5c472b7f2.jpg
         * id : 13
         */

        private String imgUrl;
        private int id;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
