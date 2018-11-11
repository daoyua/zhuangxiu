package com.zx.zhuangxiu.model;

import java.util.List;

public class PingLunLieBiaoOne {

    private int id;
    private String msg;
    private List<PingLunLieBiaoTwo> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PingLunLieBiaoTwo> getData() {
        return data;
    }

    public void setData(List<PingLunLieBiaoTwo> data) {
        this.data = data;
    }
}
