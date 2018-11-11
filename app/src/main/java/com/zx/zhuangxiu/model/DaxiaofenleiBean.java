package com.zx.zhuangxiu.model;

import java.util.List;

public class DaxiaofenleiBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"cname":"清洁地坪","id":11,"detailUrl":"/upload/20180915/8589f7fb-414e-4b97-9e37-35cc9321acc5.png,/upload/20180917/bc5d2361-8db9-404c-83c8-fc0e9bd4e24a.jpg,"},{"cname":"五金建材","id":13,"detailUrl":"/upload/20180915/9ed78d11-90de-4ad0-bfbf-9c308cd31ad7.png,/upload/20180917/64225e6b-93d7-4683-af7d-8ab428b44452.jpg,"}]
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
         * cname : 清洁地坪
         * id : 11
         * detailUrl : /upload/20180915/8589f7fb-414e-4b97-9e37-35cc9321acc5.png,/upload/20180917/bc5d2361-8db9-404c-83c8-fc0e9bd4e24a.jpg,
         */

        private String cname;
        private int id;
        private String detailUrl;

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

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

    }
}
