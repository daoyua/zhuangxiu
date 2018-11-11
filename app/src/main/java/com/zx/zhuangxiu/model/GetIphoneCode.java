package com.zx.zhuangxiu.model;

import java.util.List;

public class GetIphoneCode {
    private int id;
    private String msg;
    private List<String>data;

    @Override
    public String toString() {
        return "GetIphoneCode{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
