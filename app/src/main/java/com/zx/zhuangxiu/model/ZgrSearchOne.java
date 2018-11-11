package com.zx.zhuangxiu.model;

import java.util.List;

public class ZgrSearchOne {

    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"workYears":"12年","address":"北京市北京市东城区","fall":0,"sex":"男","registrationDate":"2018-09-25 18:38:36","userId":78,"userUrl":"/upload/20180926/9c8f8bd3-e6db-43f3-9c81-8619df022574.png","space":"","daynum":1,"realname":"武腾飞"}]
     * totalCount : 1
     */

    private int result;
    private String msg;
    private int totalCount;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * workYears : 12年
         * address : 北京市北京市东城区
         * fall : 0
         * sex : 男
         * registrationDate : 2018-09-25 18:38:36
         * userId : 78
         * userUrl : /upload/20180926/9c8f8bd3-e6db-43f3-9c81-8619df022574.png
         * space :
         * daynum : 1
         * realname : 武腾飞
         */

        private String workYears;
        private String address;
        private int fall;
        private String sex;
        private String registrationDate;
        private int userId;
        private String userUrl;
        private String space;
        private int daynum;
        private String realname;

        public String getWorkYears() {
            return workYears;
        }

        public void setWorkYears(String workYears) {
            this.workYears = workYears;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getFall() {
            return fall;
        }

        public void setFall(int fall) {
            this.fall = fall;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(String registrationDate) {
            this.registrationDate = registrationDate;
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

        public String getSpace() {
            return space;
        }

        public void setSpace(String space) {
            this.space = space;
        }

        public int getDaynum() {
            return daynum;
        }

        public void setDaynum(int daynum) {
            this.daynum = daynum;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }
}
