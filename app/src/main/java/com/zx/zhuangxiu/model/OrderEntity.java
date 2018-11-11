package com.zx.zhuangxiu.model;

import java.io.Serializable;
import java.util.List;

public class OrderEntity implements Serializable{

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"totalMoney":"306.00","list":[{"price":102,"count":1,"cdname":"钢筋辐条","picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png"},{"price":102,"count":2,"cdname":"钢筋辐条","picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png"}]}
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

    public static class DataBean implements Serializable{
        /**
         * totalMoney : 306.00
         * list : [{"price":102,"count":1,"cdname":"钢筋辐条","picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png"},{"price":102,"count":2,"cdname":"钢筋辐条","picture":"/upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png"}]
         */

        private String totalMoney;
        private List<ListBean> list;

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * price : 102
             * count : 1
             * cdname : 钢筋辐条
             * picture : /upload/20180918/b57c7fca-4e75-45b8-b0a1-c25493a00f03.png
             */

            private double price;
            private int count;
            private String cdname;
            private String picture;

            public double getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getCdname() {
                return cdname;
            }

            public void setCdname(String cdname) {
                this.cdname = cdname;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }
    }
}
