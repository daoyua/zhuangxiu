package com.zx.zhuangxiu.model;

import java.util.List;

public class ZgrSearchTwo {

    private String userName;
    private int pkId;
    private String space;
    private List<ZgrSearchThree> jskIndexWork;
    private List<ZgrSearchFour> jskSysAnnex;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public List<ZgrSearchThree> getJskIndexWork() {
        return jskIndexWork;
    }

    public void setJskIndexWork(List<ZgrSearchThree> jskIndexWork) {
        this.jskIndexWork = jskIndexWork;
    }

    public List<ZgrSearchFour> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<ZgrSearchFour> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }
}
