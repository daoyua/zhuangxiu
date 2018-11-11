package com.zx.zhuangxiu.model;

import java.util.List;

public class AddressOne {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"province":"北京市","city":"北京市","name":"赵云","county":"东城区","id":175,"tele":"15731111484","detaladdress":"红包","status":1},{"province":"北京市","city":"北京市","name":"田甜","county":"东城区","id":173,"tele":"15731111484","detaladdress":"公司","status":0}]
     * totalCount : 2
     */

    private int result;
    private String msg;
    private int totalCount;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * province : 北京市
         * city : 北京市
         * name : 赵云
         * county : 东城区
         * id : 175
         * tele : 15731111484
         * detaladdress : 红包
         * status : 1
         */

        private String province;
        private String city;
        private String name;
        private String county;
        private int id;
        private String tele;
        private String detaladdress;
        private int status;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTele() {
            return tele;
        }

        public void setTele(String tele) {
            this.tele = tele;
        }

        public String getDetaladdress() {
            return detaladdress;
        }

        public void setDetaladdress(String detaladdress) {
            this.detaladdress = detaladdress;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
