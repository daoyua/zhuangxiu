package com.zx.zhuangxiu.model;

public class MyDataTwo {
    private String filePath;
    private int pkId;

    @Override
    public String toString() {
        return "MyDataTwo{" +
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
