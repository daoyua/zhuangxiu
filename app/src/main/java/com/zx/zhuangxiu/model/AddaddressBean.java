package com.zx.zhuangxiu.model;

import android.provider.ContactsContract;

import java.util.List;

public class AddaddressBean {


    /**
     * result : 1
     * msg : 添加成功!
     * data : null
     */

    private int result;
    private String msg;
    private List<databeans> data;

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

    public List<databeans> getData() {
        return data;
    }

    public void setData(List<databeans> data) {
        this.data = data;
    }

    public static class databeans {

        private String name;
        private String tele;
        private String province;
        private String city;
        private String county;
        private String detaladdress;
        private String status;
        private int user_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTele() {
            return tele;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDetaladdress() {
            return detaladdress;
        }

        public void setDetaladdress(String detaladdress) {
            this.detaladdress = detaladdress;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
