package com.zx.zhuangxiu.model;

public class XiaoxishuliangBean {


    /**
     * result : 1
     * msg : 成功
     * data : {"wenda":0,"system":0,"pinglun":0,"pay":0,"guanzhu":0}
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
         * wenda : 0
         * system : 0
         * pinglun : 0
         * pay : 0
         * guanzhu : 0
         */

        private int wenda;
        private int system;
        private int pinglun;
        private int pay;
        private int guanzhu;

        public int getWenda() {
            return wenda;
        }

        public void setWenda(int wenda) {
            this.wenda = wenda;
        }

        public int getSystem() {
            return system;
        }

        public void setSystem(int system) {
            this.system = system;
        }

        public int getPinglun() {
            return pinglun;
        }

        public void setPinglun(int pinglun) {
            this.pinglun = pinglun;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public int getGuanzhu() {
            return guanzhu;
        }

        public void setGuanzhu(int guanzhu) {
            this.guanzhu = guanzhu;
        }
    }
}
