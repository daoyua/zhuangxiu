package com.zx.zhuangxiu.model;

import java.util.List;

public class SyJInPaiOne {

    private int id;
    private String msg;
    private List<SyJInPai> data;

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

    public List<SyJInPai> getData() {
        return data;
    }

    public void setData(List<SyJInPai> data) {
        this.data = data;
    }
}
