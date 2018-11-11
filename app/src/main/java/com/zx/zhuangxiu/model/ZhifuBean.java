package com.zx.zhuangxiu.model;

public class ZhifuBean {


    /**
     * result : 1
     * msg : 支付成功!
     * data : {"msg":"支付成功！","payResult":{"payedMoney":0,"error":null,"requestData":null,"online":false,"success":true},"success":true}
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
         * msg : 支付成功！
         * payResult : {"payedMoney":0,"error":null,"requestData":null,"online":false,"success":true}
         * success : true
         */

        private String msg;
        private PayResultBean payResult;
        private boolean success;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public PayResultBean getPayResult() {
            return payResult;
        }

        public void setPayResult(PayResultBean payResult) {
            this.payResult = payResult;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public static class PayResultBean {
            /**
             * payedMoney : 0
             * error : null
             * requestData : null
             * online : false
             * success : true
             */

            private int payedMoney;
            private Object error;
            private Object requestData;
            private boolean online;
            private boolean success;

            public int getPayedMoney() {
                return payedMoney;
            }

            public void setPayedMoney(int payedMoney) {
                this.payedMoney = payedMoney;
            }

            public Object getError() {
                return error;
            }

            public void setError(Object error) {
                this.error = error;
            }

            public Object getRequestData() {
                return requestData;
            }

            public void setRequestData(Object requestData) {
                this.requestData = requestData;
            }

            public boolean isOnline() {
                return online;
            }

            public void setOnline(boolean online) {
                this.online = online;
            }

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }
    }
}
