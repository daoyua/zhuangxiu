package com.zx.zhuangxiu.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * 首页-找工作
 * */
public class ShouYeZgzOne {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"imgUrl":"/upload/20180927/2d883a4b5711472cb19c7148104c02f1.png","number":0,"education":"好","address":"啊","name":"11","laudCount":0,"id":22,"synopsis":"好","experience":"不限"},{"imgUrl":"/upload/20180926/73fe95cdddb94ac1a58d88bb44652c0f.png","number":0,"education":"小","address":"天津","name":"00000","laudCount":0,"id":20,"synopsis":"啊","experience":"不限"},{"imgUrl":"/upload/20180926/95ef695780904e9a806f4595c015761f.png","number":0,"education":"大学","address":"北京","name":"大家","laudCount":0,"id":19,"synopsis":"好123","experience":"不限"},{"imgUrl":"/upload/20180926/f5ac64c766044ebabcf9b74f599fa965.png","number":0,"education":"初中","address":"朝阳区西直河","name":"水电安装","laudCount":0,"id":17,"synopsis":"3","experience":"不限"},{"imgUrl":"/upload/20180926/8e4651a6e8d14cb290aa9f565042e517.png","number":0,"education":"初中","address":"朝阳区西直河","name":"大理石结晶","laudCount":0,"id":16,"synopsis":"4","experience":"不限"},{"imgUrl":"/upload/20180926/ef17dbbb251f43359f303f9a94567157.png","number":0,"education":"1","address":"1","name":"1","laudCount":0,"id":15,"synopsis":"1","experience":"不限"},{"imgUrl":"/upload/20180918/d9ddcbd79f304c12a627711aecd3b615.png","number":0,"education":"大学","address":"中国","city":"石家庄","name":"装修","laudCount":0,"id":13,"synopsis":"工作不累","experience":"不限"}]
     * totalCount : 7
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
         * imgUrl : /upload/20180927/2d883a4b5711472cb19c7148104c02f1.png
         * number : 0
         * education : 好
         * address : 啊
         * name : 11
         * laudCount : 0
         * id : 22
         * synopsis : 好
         * experience : 不限
         * city : 石家庄
         */

        private String imgUrl;
        private int number;
        private String education;
        private String address;
        private String name;
        private int laudCount;
        private int id;
        private String synopsis;
        private String experience;
        private String city;

        @SerializedName("addTime")  //发起时间
        public String addTime;
        @SerializedName("startTime")
        public String startTime;
        @SerializedName("endTime")
        public String endTime;
        @SerializedName("worktypes")  //工作种类
        public String worktypes;
        @SerializedName("telenumber")  //电话
        public String telenumber;
        @SerializedName("treatment")    //福利
        public String treatment;
        @SerializedName("jobRequire")  //工作要求
        public String jobRequire;
        @SerializedName("wages")  //工作要求
        public String wages;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLaudCount() {
            return laudCount;
        }

        public void setLaudCount(int laudCount) {
            this.laudCount = laudCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
