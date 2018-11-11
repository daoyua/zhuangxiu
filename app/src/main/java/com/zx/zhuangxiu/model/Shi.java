package com.zx.zhuangxiu.model;

import java.util.List;

public class Shi {
    private String name;
    private List<String> area;

    @Override
    public String toString() {
        return "Shi{" +
                "name='" + name + '\'' +
                ", area=" + area +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }
}
