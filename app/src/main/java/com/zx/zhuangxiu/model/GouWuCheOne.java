package com.zx.zhuangxiu.model;

import java.util.List;

public class GouWuCheOne {

    private String msg;
    private int status;
    private List<GouWuCheBean> data;

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

    public List<GouWuCheBean> getData() {
        return data;
    }

    public void setData(List<GouWuCheBean> data) {
        this.data = data;
    }
}
