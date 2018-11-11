package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanGRXQFuWuXingQingOne {
    private List<MyDingDanGRXQFuWuXingQingTwo>jskIndexService;

    @Override
    public String toString() {
        return "MyDingDanGRXQFuWuXingQingOne{" +
                "jskIndexService=" + jskIndexService +
                '}';
    }

    public List<MyDingDanGRXQFuWuXingQingTwo> getJskIndexService() {
        return jskIndexService;
    }

    public void setJskIndexService(List<MyDingDanGRXQFuWuXingQingTwo> jskIndexService) {
        this.jskIndexService = jskIndexService;
    }
}
