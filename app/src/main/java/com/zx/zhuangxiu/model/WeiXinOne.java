package com.zx.zhuangxiu.model;

public class WeiXinOne {

    private int status;
    private String msg;
    private WeiXinTwo data;

    @Override
    public String toString() {
        return "WeiXinOne{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public WeiXinTwo getData() {
        return data;
    }

    public void setData(WeiXinTwo data) {
        this.data = data;
    }
}
