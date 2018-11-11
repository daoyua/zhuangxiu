package com.zx.zhuangxiu.model;

public class MyDongTaiQYTwo {
    private String filePath;//图片地址
    private int pkId;

    @Override
    public String toString() {
        return "MyDongTaiQYTwo{" +
                "filePath='" + filePath + '\'' +
                ", pkId=" + pkId +
                '}';
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPkId() {
        return pkId;
    }

    public void setPkId(int pkId) {
        this.pkId = pkId;
    }
}
