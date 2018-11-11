package com.zx.zhuangxiu.model;

public class DianzanBean {


    /**
     * result : 1
     * msg : 取消点赞成功!
     * data : {"state":2}
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
         * state : 2
         */

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
