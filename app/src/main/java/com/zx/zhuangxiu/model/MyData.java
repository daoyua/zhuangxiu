package com.zx.zhuangxiu.model;

import java.util.List;

public class MyData {
    private List<MyDataOne>data;
    private int id;
    private String msg;

    @Override
    public String toString() {
        return "MyData{" +
                "data=" + data +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public List<MyDataOne> getData() {
        return data;
    }

    public void setData(List<MyDataOne> data) {
        this.data = data;
    }

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
}
