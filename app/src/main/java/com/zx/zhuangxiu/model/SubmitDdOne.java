package com.zx.zhuangxiu.model;

public class SubmitDdOne {

    private String msg;
    private int status;
    private SubmitDdTwo data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SubmitDdTwo getData() {
        return data;
    }

    public void setData(SubmitDdTwo data) {
        this.data = data;
    }
}
