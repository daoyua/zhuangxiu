package com.zx.zhuangxiu.model;

import java.util.List;

/**
 * 首页-找工人
 * */
public class ShouYeZgrOne {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"address":"河北省石家庄市新华区中储广场","fall":4,"registrationDate":"2018-08-21 09:31:38","userId":2,"userUrl":"/manage/login/img/headurl.png","space":"人不行不能说路不平","daynum":8,"realname":"张三"},{"address":"山西省太原市小店区","fall":2,"registrationDate":"2018-08-21 09:31:51","userId":5,"userUrl":"/manage/login/img/headurl.png","daynum":8,"realname":"赵六"},{"address":"山西省临汾市","fall":1,"registrationDate":"2018-08-21 09:31:52","userId":6,"userUrl":"/manage/login/img/headurl.png","daynum":8,"realname":"展昭"}]
     * totalCount : 3
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
         * address : 河北省石家庄市新华区中储广场
         * fall : 4
         * registrationDate : 2018-08-21 09:31:38
         * userId : 2
         * userUrl : /manage/login/img/headurl.png
         * space : 人不行不能说路不平
         * daynum : 8
         * realname : 张三
         */

        private String address;
        private int fall;
        private String registrationDate;
        private int userId;
        private String userUrl;
        private String space;
        private int daynum;
        private String realname;
        private String sex;
        private int yearNum;
        private String age;
        private int number;
        private String workType;

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public int getYearNum() {
            return yearNum;
        }

        public void setYearNum(int yearNum) {
            this.yearNum = yearNum;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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
