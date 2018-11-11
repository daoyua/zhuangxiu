package com.zx.zhuangxiu.model;

public class ChengGong {
    private int id;
    private String msg;

    @Override
    public String toString() {
        return "ChengGong{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
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
}
