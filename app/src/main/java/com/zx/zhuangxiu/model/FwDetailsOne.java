package com.zx.zhuangxiu.model;

import java.util.List;

public class FwDetailsOne {

    private int id;
    private String msg;
    private List<FwDetailsTwo> data;

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

    public List<FwDetailsTwo> getData() {
        return data;
    }

    public void setData(List<FwDetailsTwo> data) {
        this.data = data;
    }
}
