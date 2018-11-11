package com.zx.zhuangxiu.model;

public class FWorkDetailsOne {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"treatment":"管3餐，","worktypes":"墙面打磨工","education":"初中","telenumber":"13693593645","jobRequire":"技术好，男，二把刀勿扰","synopsis":"北京羿之神","userId":279,"linkman":"潘经理","imgUrl":"/upload/20181019/136546c6de134c5aa63271c4bb46462d.png","wages":"350","name":"石材结晶","startTime":"2018-10-19 00:00:00","id":87,"detailUrl":"/upload/20181019/136546c6de134c5aa63271c4bb46462d.png","endTime":"2018-10-25 00:00:00"}
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
         * treatment : 管3餐，
         * worktypes : 墙面打磨工
         * education : 初中
         * telenumber : 13693593645
         * jobRequire : 技术好，男，二把刀勿扰
         * synopsis : 北京羿之神
         * userId : 279
         * linkman : 潘经理
         * imgUrl : /upload/20181019/136546c6de134c5aa63271c4bb46462d.png
         * wages : 350
         * name : 石材结晶
         * startTime : 2018-10-19 00:00:00
         * id : 87
         * detailUrl : /upload/20181019/136546c6de134c5aa63271c4bb46462d.png
         * endTime : 2018-10-25 00:00:00
         */

        private String treatment;
        private String worktypes;
        private String education;
        private String telenumber;
        private String jobRequire;
        private String synopsis;
        private int userId;
        private String linkman;
        private String imgUrl;
        private String wages;
        private String name;
        private String startTime;
        private int id;
        private String detailUrl;
        private String endTime;

        public String getTreatment() {
            return treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }

        public String getWorktypes() {
            return worktypes;
        }

        public void setWorktypes(String worktypes) {
            this.worktypes = worktypes;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getTelenumber() {
            return telenumber;
        }

        public void setTelenumber(String telenumber) {
            this.telenumber = telenumber;
        }

        public String getJobRequire() {
            return jobRequire;
        }

        public void setJobRequire(String jobRequire) {
            this.jobRequire = jobRequire;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getWages() {
            return wages;
        }

        public void setWages(String wages) {
            this.wages = wages;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }
}
