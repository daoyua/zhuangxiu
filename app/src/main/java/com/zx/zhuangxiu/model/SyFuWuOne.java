package com.zx.zhuangxiu.model;

import java.util.List;

public class SyFuWuOne {

    private int id;
    private String msg;
    private List<SyFuWuTwo> data;

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

    public List<SyFuWuTwo> getData() {
        return data;
    }

    public void setData(List<SyFuWuTwo> data) {
        this.data = data;
    }
}
