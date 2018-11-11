package com.zx.zhuangxiu.model;


import java.util.List;

public class Guo {
   private List<Sheng> list;

    @Override
    public String toString() {
        return "Guo{" +
                "list=" + list +
                '}';
    }

    public List<Sheng> getList() {
        return list;
    }

    public void setList(List<Sheng> list) {
        this.list = list;
    }
}
