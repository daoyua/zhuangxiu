package com.zx.zhuangxiu.model;

public class ZhifubaoBean {


    /**
     * result : 1
     * msg : 支付成功!
     * data : {"msg":"请确认支付","payResult":{"payedMoney":199,"error":"","requestData":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018080160780938&biz_content=%7B%22body%22%3A%2220180917133354594%22%2C%22out_trade_no%22%3A%2220180917133354594%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%2220180917133354594%22%2C%22total_amount%22%3A%22199.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=%2Fpay%2Fnotify%2FalipayAppPay&sign=UiuVLfmzPZcwRaHhdqqkX2kzkjJmOaA%2F51Hkq30qm9xepeAsxZOY8uZR4TiSb4Ns6tMmDZVjZLBc0zyPTD2ngj5yhVpkJg8doXOYRTvPtCwn2T5NQw5%2F3xM%2BeDBZABYV07e7gPP13oZYbCV8ZhRmz5q0BiBm74e9TeoJca7f%2FnZnhySyxPbjwiaahxQf2SDlJ5flhe0tdaavlU525%2BX%2FzNgDjHTfoZQ32NbhwgfBONvf32JZ15%2FDwXDWtIqJ03oosvVs2jJBgXW5GFWk2E%2FPrqsiFGXgyN1wWUq0SkkYboBaAI4RkVS5UHiouFtmWHRiv9ZPR3FGwn29Kh6%2FIrlGmA%3D%3D&sign_type=RSA2&timestamp=2018-09-17+13%3A34%3A48&version=1.0","online":true,"success":true},"success":true}
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
         * msg : 请确认支付
         * payResult : {"payedMoney":199,"error":"","requestData":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018080160780938&biz_content=%7B%22body%22%3A%2220180917133354594%22%2C%22out_trade_no%22%3A%2220180917133354594%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%2220180917133354594%22%2C%22total_amount%22%3A%22199.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=%2Fpay%2Fnotify%2FalipayAppPay&sign=UiuVLfmzPZcwRaHhdqqkX2kzkjJmOaA%2F51Hkq30qm9xepeAsxZOY8uZR4TiSb4Ns6tMmDZVjZLBc0zyPTD2ngj5yhVpkJg8doXOYRTvPtCwn2T5NQw5%2F3xM%2BeDBZABYV07e7gPP13oZYbCV8ZhRmz5q0BiBm74e9TeoJca7f%2FnZnhySyxPbjwiaahxQf2SDlJ5flhe0tdaavlU525%2BX%2FzNgDjHTfoZQ32NbhwgfBONvf32JZ15%2FDwXDWtIqJ03oosvVs2jJBgXW5GFWk2E%2FPrqsiFGXgyN1wWUq0SkkYboBaAI4RkVS5UHiouFtmWHRiv9ZPR3FGwn29Kh6%2FIrlGmA%3D%3D&sign_type=RSA2&timestamp=2018-09-17+13%3A34%3A48&version=1.0","online":true,"success":true}
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
             * payedMoney : 199
             * error :
             * requestData : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018080160780938&biz_content=%7B%22body%22%3A%2220180917133354594%22%2C%22out_trade_no%22%3A%2220180917133354594%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%2220180917133354594%22%2C%22total_amount%22%3A%22199.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=%2Fpay%2Fnotify%2FalipayAppPay&sign=UiuVLfmzPZcwRaHhdqqkX2kzkjJmOaA%2F51Hkq30qm9xepeAsxZOY8uZR4TiSb4Ns6tMmDZVjZLBc0zyPTD2ngj5yhVpkJg8doXOYRTvPtCwn2T5NQw5%2F3xM%2BeDBZABYV07e7gPP13oZYbCV8ZhRmz5q0BiBm74e9TeoJca7f%2FnZnhySyxPbjwiaahxQf2SDlJ5flhe0tdaavlU525%2BX%2FzNgDjHTfoZQ32NbhwgfBONvf32JZ15%2FDwXDWtIqJ03oosvVs2jJBgXW5GFWk2E%2FPrqsiFGXgyN1wWUq0SkkYboBaAI4RkVS5UHiouFtmWHRiv9ZPR3FGwn29Kh6%2FIrlGmA%3D%3D&sign_type=RSA2&timestamp=2018-09-17+13%3A34%3A48&version=1.0
             * online : true
             * success : true
             */

            private double payedMoney;
            private String error;
            private String requestData;
            private boolean online;
            private boolean success;

            public double getPayedMoney() {
                return payedMoney;
            }

            public void setPayedMoney(int payedMoney) {
                this.payedMoney = payedMoney;
            }

            public String getError() {
                return error;
            }

            public void setError(String error) {
                this.error = error;
            }

            public String getRequestData() {
                return requestData;
            }

            public void setRequestData(String requestData) {
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
