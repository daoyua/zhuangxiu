package com.zx.zhuangxiu.model;

import java.util.List;

public class ZhijiejinruBean {


    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"img_url":"/upload/20180829/6747467b-7fbe-4980-853a-a11a3cb67de2.jpg","id":7}]
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
         * img_url : /upload/20180829/6747467b-7fbe-4980-853a-a11a3cb67de2.jpg
         * id : 7
         */

        private String img_url;
        private int id;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
