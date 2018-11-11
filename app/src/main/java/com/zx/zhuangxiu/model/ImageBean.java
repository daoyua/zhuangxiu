package com.zx.zhuangxiu.model;

public class ImageBean {

    /**
     * result : 1
     * msg : 上传成功!
     * data : {"url":"/upload/20180913/13/2c05610ddff547b28251aa6047e18bf3.jpg"}
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
         * url : /upload/20180913/13/2c05610ddff547b28251aa6047e18bf3.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
