package com.zx.zhuangxiu.model;

public class ShopFreeBean {

    /**
     * result : 1
     * msg : 操作成功!
     * data : {"paylogId":"220"}
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
         * paylogId : 220
         */

        private String paylogId;

        public String getPaylogId() {
            return paylogId;
        }

        public void setPaylogId(String paylogId) {
            this.paylogId = paylogId;
        }
    }
}
