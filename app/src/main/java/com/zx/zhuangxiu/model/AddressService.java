package com.zx.zhuangxiu.model;

public class AddressService {

    /**
     * result : 1
     * msg : 查询成功！！
     * {result=0/1,data={}}
     */

    private int result;
    private String  msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AddressService{" +
                "result=" + result +
                ", msg='" + msg + '\'' +
                '}';
    }
}
