package com.zx.zhuangxiu.model;

public class XiugainichengBean {


    /**
     * result : 0
     * msg : 获取失败!nested exception is org.apache.ibatis.exceptions.PersistenceException:
     ### Error updating database.  Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for personalDao.updateUserName
     ### Cause: java.lang.IllegalArgumentException: Mapped Statements collection does not contain value for personalDao.updateUserName
     * data :
     */

    private int result;
    private String msg;
    private String data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
