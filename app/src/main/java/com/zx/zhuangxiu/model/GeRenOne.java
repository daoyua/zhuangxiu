package com.zx.zhuangxiu.model;

import java.util.List;

public class GeRenOne {

    private int id;
    private String msg;
    private List<GeRenTwo> data;

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

    public List<GeRenTwo> getData() {
        return data;
    }

    public void setData(List<GeRenTwo> data) {
        this.data = data;
    }
}
