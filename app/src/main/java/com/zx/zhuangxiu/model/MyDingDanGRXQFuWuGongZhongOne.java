package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDingDanGRXQFuWuGongZhongOne {
    private List<MyDingDanGRXQFuWuGongZhongTwo>jskIndexService;

    @Override
    public String toString() {
        return "MyDingDanGRXQFuWuGongZhongOne{" +
                "jskIndexService=" + jskIndexService +
                '}';
    }

    public List<MyDingDanGRXQFuWuGongZhongTwo> getJskIndexService() {
        return jskIndexService;
    }

    public void setJskIndexService(List<MyDingDanGRXQFuWuGongZhongTwo> jskIndexService) {
        this.jskIndexService = jskIndexService;
    }
}
