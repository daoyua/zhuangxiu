package com.zx.zhuangxiu.model;

import java.util.List;

public class FuWuDetailsOne {

    private int id;
    private String msg;
    private List<FuWuDetailsTwo> data;

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

    public List<FuWuDetailsTwo> getData() {
        return data;
    }

    public void setData(List<FuWuDetailsTwo> data) {
        this.data = data;
    }
}
