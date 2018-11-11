package com.zx.zhuangxiu.model;

import java.util.List;

public class ZhuCe {
    private List<Integer>data;
    private int id;
    private String msg;

    @Override
    public String toString() {
        return "ZhuCe{" +
                "data=" + data +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
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
