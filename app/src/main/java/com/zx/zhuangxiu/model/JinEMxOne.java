package com.zx.zhuangxiu.model;

import java.util.List;

public class JinEMxOne {

    private int id;
    private String msg;
    private List<JinEMxTwo> data;

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

    public List<JinEMxTwo> getData() {
        return data;
    }

    public void setData(List<JinEMxTwo> data) {
        this.data = data;
    }
}
