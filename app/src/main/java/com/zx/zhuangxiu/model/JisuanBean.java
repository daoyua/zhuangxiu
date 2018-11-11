package com.zx.zhuangxiu.model;

import java.util.List;

public class JisuanBean {

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"attributeId":12,"totalMoney":"200.00","list":[{"price":200,"cdname":"阳台小公园","count":1,"picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"}]}
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
         * attributeId : 12
         * totalMoney : 200.00
         * list : [{"price":200,"cdname":"阳台小公园","count":1,"picture":"/upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png"}]
         */

        private int attributeId;
        private String totalMoney;
        private List<ListBean> list;

        public int getAttributeId() {
            return attributeId;
        }

        public void setAttributeId(int attributeId) {
            this.attributeId = attributeId;
        }

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

        public static class ListBean {
            /**
             * price : 200
             * cdname : 阳台小公园
             * count : 1
             * picture : /upload/20180829/4091fcd7-4a1c-4df3-94b2-88b86bece632.png
             */

            private double price;
            private String cdname;
            private int count;
            private String picture;

            public double getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getCdname() {
                return cdname;
            }

            public void setCdname(String cdname) {
                this.cdname = cdname;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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
