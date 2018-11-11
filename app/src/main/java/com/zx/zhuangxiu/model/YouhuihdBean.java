package com.zx.zhuangxiu.model;

import java.util.List;

public class YouhuihdBean {


    /**
     * result : 1
     * msg : 成功！！！
     * data : [{"wn_image":"/upload/20180828/472d6d45-7ad3-449f-8ca6-4ada9fcfd6d9.jpg","wn_content":"656565","wn_title":"32323","time":"2018-08-28 17:20:08"}]
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
         * wn_image : /upload/20180828/472d6d45-7ad3-449f-8ca6-4ada9fcfd6d9.jpg
         * wn_content : 656565
         * wn_title : 32323
         * time : 2018-08-28 17:20:08
         */

        private String wn_image;
        private String wn_content;
        private String wn_title;
        private String time;

        public String getWn_image() {
            return wn_image;
        }

        public void setWn_image(String wn_image) {
            this.wn_image = wn_image;
        }

        public String getWn_content() {
            return wn_content;
        }

        public void setWn_content(String wn_content) {
            this.wn_content = wn_content;
        }

        public String getWn_title() {
            return wn_title;
        }

        public void setWn_title(String wn_title) {
            this.wn_title = wn_title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
