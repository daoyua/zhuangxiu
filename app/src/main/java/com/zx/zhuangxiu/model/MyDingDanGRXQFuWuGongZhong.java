package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanGRXQFuWuGongZhong {
    private List<MyDingDanGRXQFuWuGongZhongOne> data;
    private int id;
    private String msg;

    @Override
    public String toString() {
        return "MyDingDanGRXQFuWuGongZhong{" +
                "data=" + data +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public List<MyDingDanGRXQFuWuGongZhongOne> getData() {
        return data;
    }

    public void setData(List<MyDingDanGRXQFuWuGongZhongOne> data) {
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
