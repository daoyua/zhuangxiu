package com.zx.zhuangxiu.model;

public class ZhaoGRxqBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"realName":"张三","address":"河北省石家庄市新华区中储广场","telenumber":"12345698746","workType":"瓦工","userId":2,"userUrl":"/manage/login/img/headurl.png","registerDate":"2018-08-21"}
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
         * realName : 张三
         * address : 河北省石家庄市新华区中储广场
         * telenumber : 12345698746
         * workType : 瓦工
         * userId : 2
         * userUrl : /manage/login/img/headurl.png
         * registerDate : 2018-08-21
         */

        private String realName;
        private String address;
        private String telenumber;
        private String workType;
        private int userId;
        private String userUrl;
        private String registerDate;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserUrl() {
            return userUrl;
        }

        public void setUserUrl(String userUrl) {
            this.userUrl = userUrl;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }
    }
}
