package com.zx.zhuangxiu.model;

public class DeleteaddressBean {


    /**
     * result : 1
     * msg : 删除失败!
     * data : null
     */

    private int result;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
