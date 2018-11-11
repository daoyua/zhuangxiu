package com.zx.zhuangxiu.model;

import java.util.List;

public class JiiesuandizhiBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"province":"河北省","city":"石家庄市","name":"高","county":"新华区","id":153,"tele":"15100341201","detaladdress":"你猜我在那","status":1}]
     * totalCount : 1
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
         * province : 河北省
         * city : 石家庄市
         * name : 高
         * county : 新华区
         * id : 153
         * tele : 15100341201
         * detaladdress : 你猜我在那
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
