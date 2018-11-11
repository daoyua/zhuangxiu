package com.zx.zhuangxiu.model;

import java.util.List;

public class WlBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"orderItems":[{"buyprice":0.01,"cdname":"狗狗","count":1,"id":50,"picture":"/upload/20181018/6a54e3c4-b84a-4bc4-89e2-95cf51ff4f49.png"}],"order":{"receivername":"田甜","receiverAddress":"公司","orderno":"20181018185830292","payType":"微信App支付","addTime":"2018-10-18 18:58:30","delivercode":"1230","totalprice":0.01,"paystatus":1,"id":1010,"delivername":"云达通","delivertime":"2018-10-18 19:01:11"}}
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
         * orderItems : [{"buyprice":0.01,"cdname":"狗狗","count":1,"id":50,"picture":"/upload/20181018/6a54e3c4-b84a-4bc4-89e2-95cf51ff4f49.png"}]
         * order : {"receivername":"田甜","receiverAddress":"公司","orderno":"20181018185830292","payType":"微信App支付","addTime":"2018-10-18 18:58:30","delivercode":"1230","totalprice":0.01,"paystatus":1,"id":1010,"delivername":"云达通","delivertime":"2018-10-18 19:01:11"}
         */

        private OrderBean order;
        private List<OrderItemsBean> orderItems;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        public static class OrderBean {
            /**
             * receivername : 田甜
             * receiverAddress : 公司
             * orderno : 20181018185830292
             * payType : 微信App支付
             * addTime : 2018-10-18 18:58:30
             * delivercode : 1230
             * totalprice : 0.01
             * paystatus : 1
             * id : 1010
             * delivername : 云达通
             * delivertime : 2018-10-18 19:01:11
             */

            private String receivername;
            private String receiverAddress;
            private String orderno;
            private String payType;
            private String addTime;
            private String delivercode;
            private double totalprice;
            private int paystatus;
            private int id;
            private String delivername;
            private String delivertime;
            private int deliverstatus;
            private int orderstatus;

            public int getOrderstatus() {
                return orderstatus;
            }

            public void setOrderstatus(int orderstatus) {
                this.orderstatus = orderstatus;
            }

            public int getDeliverstatus() {
                return deliverstatus;
            }

            public void setDeliverstatus(int deliverstatus) {
                this.deliverstatus = deliverstatus;
            }

            public String getReceivername() {
                return receivername;
            }

            public void setReceivername(String receivername) {
                this.receivername = receivername;
            }

            public String getReceiverAddress() {
                return receiverAddress;
            }

            public void setReceiverAddress(String receiverAddress) {
                this.receiverAddress = receiverAddress;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getDelivercode() {
                return delivercode;
            }

            public void setDelivercode(String delivercode) {
                this.delivercode = delivercode;
            }

            public double getTotalprice() {
                return totalprice;
            }

            public void setTotalprice(double totalprice) {
                this.totalprice = totalprice;
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

            public String getDelivername() {
                return delivername;
            }

            public void setDelivername(String delivername) {
                this.delivername = delivername;
            }

            public String getDelivertime() {
                return delivertime;
            }

            public void setDelivertime(String delivertime) {
                this.delivertime = delivertime;
            }
        }

        public static class OrderItemsBean {
            /**
             * buyprice : 0.01
             * cdname : 狗狗
             * count : 1
             * id : 50
             * picture : /upload/20181018/6a54e3c4-b84a-4bc4-89e2-95cf51ff4f49.png
             */

            private double buyprice;
            private String cdname;
            private int count;
            private int id;
            private String picture;
            private String infoName;

            public String getInfoName() {
                return infoName;
            }

            public void setInfoName(String infoName) {
                this.infoName = infoName;
            }

            public double getBuyprice() {
                return buyprice;
            }

            public void setBuyprice(double buyprice) {
                this.buyprice = buyprice;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
