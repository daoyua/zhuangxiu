package com.zx.zhuangxiu.model;

import java.util.List;

public class DetailsOne {

    private int id;
    private String msg;
    private List<DetailsTwo> data;


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

    public List<DetailsTwo> getData() {
        return data;
    }

    public void setData(List<DetailsTwo> data) {
        this.data = data;
    }
}
