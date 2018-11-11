package com.zx.zhuangxiu.model;

import java.util.List;

public class SyJInPai {

    private List<SyJInPaiTwo> jskSysAnnex;
    private int pkId;

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }

    public List<SyJInPaiTwo> getJskSysAnnex() {
        return jskSysAnnex;
    }

    public void setJskSysAnnex(List<SyJInPaiTwo> jskSysAnnex) {
        this.jskSysAnnex = jskSysAnnex;
    }
}
