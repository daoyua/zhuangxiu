package com.zx.zhuangxiu.model;

import java.util.List;

public class Sheng {
    private String name;
    private List<Shi> city;

    @Override
    public String toString() {
        return "Sheng{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shi> getCity() {
        return city;
    }

    public void setCity(List<Shi> city) {
        this.city = city;
    }
}
