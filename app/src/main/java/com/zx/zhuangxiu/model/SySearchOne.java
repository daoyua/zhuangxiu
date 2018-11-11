package com.zx.zhuangxiu.model;

import java.util.List;

public class SySearchOne {

    private int id;
    private String msg;
    private List<SySearchTwo> data;

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

    public List<SySearchTwo> getData() {
        return data;
    }

    public void setData(List<SySearchTwo> data) {
        this.data = data;
    }
}
