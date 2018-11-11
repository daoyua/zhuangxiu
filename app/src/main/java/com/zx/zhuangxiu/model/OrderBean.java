package com.zx.zhuangxiu.model;

public class OrderBean {

    /**
     * result : 1
     * msg : 生成订单成功!
     * data : {"paylogId":128}
     */

    private int result;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * paylogId : 128
         */

        private int paylogId;

        public int getPaylogId() {
            return paylogId;
        }

        public void setPaylogId(int paylogId) {
            this.paylogId = paylogId;
        }
    }
}
