package com.zx.zhuangxiu.model;

import java.util.List;

public class JiFenShangCheng {
    private List<JiFenShangChengOne>data;
    private String msg;
    private int id;

    @Override
    public String toString() {
        return "JiFenShangCheng{" +
                "data=" + data +
                ", msg='" + msg + '\'' +
                ", id=" + id +
                '}';
    }

    public List<JiFenShangChengOne> getData() {
        return data;
    }

    public void setData(List<JiFenShangChengOne> data) {
        this.data = data;
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
}
