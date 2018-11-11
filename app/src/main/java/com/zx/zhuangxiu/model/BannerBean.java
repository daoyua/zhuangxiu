package com.zx.zhuangxiu.model;

import java.util.List;

public class BannerBean {

    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"wi_title":"2","id":9,"wi_address":"/upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png","wi_releases":1},{"wi_title":"12121212","id":10,"wi_address":"/upload/20180828/42e8b04e-6888-4a28-85d4-4e245e1da8de.png","wi_releases":1}]
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
         * wi_title : 2
         * id : 9
         * wi_address : /upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png
         * wi_releases : 1
         */

        private String wi_title;
        private int id;
        private String wi_address;
        private int wi_releases;
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWi_title() {
            return wi_title;
        }

        public void setWi_title(String wi_title) {
            this.wi_title = wi_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWi_address() {
            return wi_address;
        }

        public void setWi_address(String wi_address) {
            this.wi_address = wi_address;
        }

        public int getWi_releases() {
            return wi_releases;
        }

        public void setWi_releases(int wi_releases) {
            this.wi_releases = wi_releases;
        }
    }
}
