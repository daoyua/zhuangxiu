package com.zx.zhuangxiu.model;

import java.util.List;

public class SpTuiJianTwo {

    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"id":2,"picture":"/upload/20180829/b341f731-06bd-425a-9f9e-64c2ff36b8fc.png"},{"id":1,"picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"}]
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
         * id : 2
         * picture : /upload/20180829/b341f731-06bd-425a-9f9e-64c2ff36b8fc.png
         */

        private int id;
        private String picture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
