package com.zx.zhuangxiu.model;

public class FisrstLoginBean {


    /**
     * result : 0
     * msg : 验证码不正确!
     * data :
     */


        private int result;
        private String msg;
        private Data data;
        public void setResult(int result) {
            this.result = result;
        }
        public int getResult() {
            return result;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }

    public class Data {

        private String address;
        private String nickName;
        private String telenumber;
        private int state;
        private int userId;
        private String userUrl;
        public void setAddress(String address) {
            this.address = address;
        }
        public String getAddress() {
            return address;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
        public String getNickName() {
            return nickName;
        }

        public void setTelenumber(String telenumber) {
            this.telenumber = telenumber;
        }
        public String getTelenumber() {
            return telenumber;
        }

        public void setState(int state) {
            this.state = state;
        }
        public int getState() {
            return state;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getUserId() {
            return userId;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }
        public String getUserUrl() {
            return userUrl;
        }

    }
}
