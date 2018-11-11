package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanQYandSJ {
    private String msg;
    private int id;
    private List<MyDingDanQYandSJOne>data;

    @Override
    public String toString() {
        return "MyDingDanQYandSJ{" +
                "msg='" + msg + '\'' +
                ", id=" + id +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MyDingDanQYandSJOne> getData() {
        return data;
    }

    public void setData(List<MyDingDanQYandSJOne> data) {
        this.data = data;
    }
}
