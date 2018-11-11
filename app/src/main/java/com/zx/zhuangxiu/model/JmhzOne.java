package com.zx.zhuangxiu.model;

import java.util.List;

public class JmhzOne {

    private int id;
    private String msg;
    private List<JmhzTwo> data;


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

    public List<JmhzTwo> getData() {
        return data;
    }

    public void setData(List<JmhzTwo> data) {
        this.data = data;
    }
}
