package com.zx.zhuangxiu.model;

import java.util.List;

public class DaShiQuanLieBiaoOne {

    private int id;
    private String msg;
    private List<DaShiQuanLieBiaoTwo> data;

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

    public List<DaShiQuanLieBiaoTwo> getData() {
        return data;
    }

    public void setData(List<DaShiQuanLieBiaoTwo> data) {
        this.data = data;
    }
}
