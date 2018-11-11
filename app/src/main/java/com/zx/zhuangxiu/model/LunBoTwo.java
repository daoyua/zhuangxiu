package com.zx.zhuangxiu.model;

import java.util.List;

public class LunBoTwo {

    private String content;
    private int createId;
    private long createTime;
    private boolean isShow;

    private List<LunBoThree> jskSysAnnex;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public List<LunBoThree> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<LunBoThree> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }
}
