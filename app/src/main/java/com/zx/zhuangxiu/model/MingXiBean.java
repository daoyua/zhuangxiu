package com.zx.zhuangxiu.model;

import java.util.List;

public class MingXiBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"remark":"通知：职平富消费了金额￥5.50,您获得了订单收入!","time":"2018-10-18 18:16:27","status":1}]
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
         * remark : 通知：职平富消费了金额￥5.50,您获得了订单收入!
         * time : 2018-10-18 18:16:27
         * status : 1
         */

        private String remark;
        private String time;
        private int status;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
