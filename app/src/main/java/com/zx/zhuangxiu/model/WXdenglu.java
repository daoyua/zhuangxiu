package com.zx.zhuangxiu.model;

public class WXdenglu {

    /**
     * result : 1
     * msg : 新用户，请前往注册!
     * data : {"access_token":"14_3VGdtti3E9miH8s2CMGk5W8tDkC7594UzZT48tOQrEVGdliqSiy08mv5kVLCpbMi8KXnAi8wZi5q8y-Es5NommzaIIpoGQcgDnTcrJ59C7E","openId":"omMJ71byyvuX5si0bCyL7EVlYczY","state":0}
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
         * access_token : 14_3VGdtti3E9miH8s2CMGk5W8tDkC7594UzZT48tOQrEVGdliqSiy08mv5kVLCpbMi8KXnAi8wZi5q8y-Es5NommzaIIpoGQcgDnTcrJ59C7E
         * openId : omMJ71byyvuX5si0bCyL7EVlYczY
         * state : 0
         */

        private String access_token;
        private String openId;
        private int state;
        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
