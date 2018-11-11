package com.zx.zhuangxiu.model;

public class LunbofenleiBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"id":7,"detailUrl":"/upload/20180829/1a5a14e0-3bed-4899-b62e-bf9c401d50e3.jpg,/upload/20180829/df40c53e-bed9-4224-9de2-621008928f33.jpg,"}
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
         * id : 7
         * detailUrl : /upload/20180829/1a5a14e0-3bed-4899-b62e-bf9c401d50e3.jpg,/upload/20180829/df40c53e-bed9-4224-9de2-621008928f33.jpg,
         */

        private int id;
        private String detailUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }
    }
}
