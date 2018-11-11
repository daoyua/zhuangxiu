package com.zx.zhuangxiu.model;

import java.util.List;

public class GwcDingDanTwo {

    private List<GwcDingDanThree> orderDetailList;
    private String pkId;


    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public List<GwcDingDanThree> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<GwcDingDanThree> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
