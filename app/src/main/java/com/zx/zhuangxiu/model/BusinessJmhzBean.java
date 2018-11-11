package com.zx.zhuangxiu.model;

import java.util.List;

public class BusinessJmhzBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"address":"123123","businessimg":"","num":1,"creattime":1535523369000,"require":"123","userId":1,"valid":1535644800000,"itemimg":"","companyname":"123","shopimg":"","paystatus":1,"id":6,"username":"123","info":"123"},{"address":"123123","businessimg":"","num":22,"creattime":1534918303000,"require":"ss","userId":1,"valid":1533484800000,"itemimg":"","companyname":"ss","shopimg":"","paystatus":1,"id":5,"username":"ss","info":"ss"}]
     */

    private int result;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 123123
         * businessimg :
         * num : 1
         * creattime : 1535523369000
         * require : 123
         * userId : 1
         * valid : 1535644800000
         * itemimg :
         * companyname : 123
         * shopimg :
         * paystatus : 1
         * id : 6
         * username : 123
         * info : 123
         */

        private String address;
        private String businessimg;
        private long num;
        private long creattime;
        private String require;
        private int userId;
        private long valid;
        private String itemimg;
        private String companyname;
        private String shopimg;
        private int paystatus;
        private int id;
        private String username;
        private String info;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessimg() {
            return businessimg;
        }

        public void setBusinessimg(String businessimg) {
            this.businessimg = businessimg;
        }

        public long getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public long getCreattime() {
            return creattime;
        }

        public void setCreattime(long creattime) {
            this.creattime = creattime;
        }

        public String getRequire() {
            return require;
        }

        public void setRequire(String require) {
            this.require = require;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getValid() {
            return valid;
        }

        public void setValid(long valid) {
            this.valid = valid;
        }

        public String getItemimg() {
            return itemimg;
        }

        public void setItemimg(String itemimg) {
            this.itemimg = itemimg;
        }

        public String getCompanyname() {
            return companyname;
        }

        public void setCompanyname(String companyname) {
            this.companyname = companyname;
        }

        public String getShopimg() {
            return shopimg;
        }

        public void setShopimg(String shopimg) {
            this.shopimg = shopimg;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
