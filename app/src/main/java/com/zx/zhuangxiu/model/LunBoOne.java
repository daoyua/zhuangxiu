package com.zx.zhuangxiu.model;

import java.util.List;

public class LunBoOne {

    private int id;
    private String msg;
    private List<LunBoTwo> data;

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

    public List<LunBoTwo> getData() {
        return data;
    }

    public void setData(List<LunBoTwo> data) {
        this.data = data;
    }
}
