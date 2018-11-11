package com.zx.zhuangxiu.model;

import java.util.List;

public class MyOrderBean {

    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"totalcount":1,"orderno":"20181010184956837","orderstatus":1,"totalprice":10000,"id":884,"itemlist":[{"itemId":917,"picUrl":"/upload/20181008/922ebbba-a600-4e73-9fd2-3f954836437e.png","totalID":5,"orderno":"20181010184956837","productId":1,"buyprice":10000,"price":10000,"count":1,"cdname":"阳台小公园"}]},{"totalcount":1,"orderno":"20181010180816342","orderstatus":1,"totalprice":10000,"id":864,"itemlist":[{"itemId":897,"picUrl":"/upload/20181008/922ebbba-a600-4e73-9fd2-3f954836437e.png","totalID":5,"orderno":"20181010180816342","productId":1,"buyprice":10000,"price":10000,"count":1,"cdname":"阳台小公园"}]},{"totalcount":1,"orderno":"20180921094805381","orderstatus":1,"totalprice":199,"id":583,"itemlist":[{"itemId":611,"picUrl":"/upload/20180919/526bf1f7-0dd3-453b-9963-24b985700f54.png","totalID":5,"orderno":"20180921094805381","productId":2,"buyprice":199,"price":199,"count":1,"cdname":"女王范\u2014\u2014项目装修"}]}]
     * totalCount : 3
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
         * totalcount : 1
         * orderno : 20181010184956837
         * orderstatus : 1
         * totalprice : 10000
         * id : 884
         * itemlist : [{"itemId":917,"picUrl":"/upload/20181008/922ebbba-a600-4e73-9fd2-3f954836437e.png","totalID":5,"orderno":"20181010184956837","productId":1,"buyprice":10000,"price":10000,"count":1,"cdname":"阳台小公园"}]
         */

        private int totalcount;
        private String orderno;
        private int orderstatus;
        private double totalprice;
        private int id;
        private List<ItemlistBean> itemlist;

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public double getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(int totalprice) {
            this.totalprice = totalprice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ItemlistBean> getItemlist() {
            return itemlist;
        }

        public void setItemlist(List<ItemlistBean> itemlist) {
            this.itemlist = itemlist;
        }

        public static class ItemlistBean {
            /**
             * itemId : 917
             * picUrl : /upload/20181008/922ebbba-a600-4e73-9fd2-3f954836437e.png
             * totalID : 5
             * orderno : 20181010184956837
             * productId : 1
             * buyprice : 10000
             * price : 10000
             * count : 1
             * cdname : 阳台小公园
             */

            private int itemId;
            private String picUrl;
            private int totalID;
            private String orderno;
            private int productId;
            private double buyprice;
            private double price;
            private int count;
            private String cdname;

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public int getTotalID() {
                return totalID;
            }

            public void setTotalID(int totalID) {
                this.totalID = totalID;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public double getBuyprice() {
                return buyprice;
            }

            public void setBuyprice(int buyprice) {
                this.buyprice = buyprice;
            }

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
        }
    }
}
