package com.zx.zhuangxiu.model;

public class MydexinxiBean {

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"score":0,"address":"北京市北京市东城区","balance":0,"countWaitPayOrder":12,"telenumber":"14703114695","workType":"石材类","nickname":"","userType":0,"userName":"","userId":52,"countWaitShopOrder":0,"userUrl":"/upload/20180915/0983a585-b71e-4b9a-9036-d647f76f24b5.png"}
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
         * score : 0
         * address : 北京市北京市东城区
         * balance : 0
         * countWaitPayOrder : 12
         * telenumber : 14703114695
         * workType : 石材类
         * nickname :
         * userType : 0
         * userName :
         * userId : 52
         * countWaitShopOrder : 0
         * userUrl : /upload/20180915/0983a585-b71e-4b9a-9036-d647f76f24b5.png
         */

        private int score;
        private String address;
        private double balance;
        private int countWaitPayOrder;
        private String telenumber;
        private String workType;
        private String nickname;
        private int userType;
        private String userName;
        private int userId;
        private int countWaitShopOrder;
        private String userUrl;
        private String userPwd;
        private int allow;

        public int getAllow() {
            return allow;
        }

        public void setAllow(int allow) {
            this.allow = allow;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getCountWaitPayOrder() {
            return countWaitPayOrder;
        }

        public void setCountWaitPayOrder(int countWaitPayOrder) {
            this.countWaitPayOrder = countWaitPayOrder;
        }

        public String getTelenumber() {
            return telenumber;
        }

        public void setTelenumber(String telenumber) {
            this.telenumber = telenumber;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCountWaitShopOrder() {
            return countWaitShopOrder;
        }

        public void setCountWaitShopOrder(int countWaitShopOrder) {
            this.countWaitShopOrder = countWaitShopOrder;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }
    }
}
