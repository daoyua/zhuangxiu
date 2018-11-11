package com.zx.zhuangxiu.model;

public class MypersonalcenterBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"score":100,"address":"河北省石家庄市新华区中储广场","balance":105,"countWaitPayOrder":35,"telenumber":"12345698746","workType":"瓦工","userType":1,"userName":"张三","userId":2,"countWaitShopOrder":2,"userUrl":"/manage/login/img/headurl.png"}
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

    public static class DataBean extends MypersonalcenterBean {
        /**
         * score : 100
         * address : 河北省石家庄市新华区中储广场
         * balance : 105
         * countWaitPayOrder : 35
         * telenumber : 12345698746
         * workType : 瓦工
         * userType : 1
         * userName : 张三
         * userId : 2
         * countWaitShopOrder : 2
         * userUrl : /manage/login/img/headurl.png
         */

        private int score;
        private String address;
        private int balance;
        private int countWaitPayOrder;
        private String telenumber;
        private String workType;
        private int userType;
        private String userName;
        private int userId;
        private int countWaitShopOrder;
        private String userUrl;

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

        public int getBalance() {
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
