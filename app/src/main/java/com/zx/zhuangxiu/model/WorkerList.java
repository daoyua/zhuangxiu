package com.zx.zhuangxiu.model;

import java.util.List;

public class WorkerList {
private String id;
private List<WorkerListItem> data;
private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<WorkerListItem> getData() {
        return data;
    }

    public void setData(List<WorkerListItem> data) {
        this.data = data;
    }
}
