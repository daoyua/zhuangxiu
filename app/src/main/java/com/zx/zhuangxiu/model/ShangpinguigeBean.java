package com.zx.zhuangxiu.model;

import java.util.List;

public class ShangpinguigeBean {
    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"specName":"产品规格：","id":33,"itemlist":[{"specOne":40,"datalist":[{"picUrl":"/upload/20181011/6cbb675d-8abe-4fdb-8454-1af0faa21fed.jpg","money":0.01,"stock":100}],"name":"一次性","specTwo":0,"infolist":[]},{"specOne":41,"datalist":[{"picUrl":"/upload/20181011/148e132d-c9ca-4a38-bf64-6a7c7b22fa55.jpg","money":0.01,"stock":1550}],"name":"永久性","specTwo":0,"infolist":[]}]}]
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
         * specName : 产品规格：
         * id : 33
         * itemlist : [{"specOne":40,"datalist":[{"picUrl":"/upload/20181011/6cbb675d-8abe-4fdb-8454-1af0faa21fed.jpg","money":0.01,"stock":100}],"name":"一次性","specTwo":0,"infolist":[]},{"specOne":41,"datalist":[{"picUrl":"/upload/20181011/148e132d-c9ca-4a38-bf64-6a7c7b22fa55.jpg","money":0.01,"stock":1550}],"name":"永久性","specTwo":0,"infolist":[]}]
         */

        private String specName;
        private int id;
        private List<ItemlistBean> itemlist;

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
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
             * specOne : 40
             * datalist : [{"picUrl":"/upload/20181011/6cbb675d-8abe-4fdb-8454-1af0faa21fed.jpg","money":0.01,"stock":100}]
             * name : 一次性
             * specTwo : 0
             * infolist : []
             */

            private int specOne;
            private String name;
            private int specTwo;
            private List<DatalistBean> datalist;
            private List<?> infolist;

            public int getSpecOne() {
                return specOne;
            }

            public void setSpecOne(int specOne) {
                this.specOne = specOne;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSpecTwo() {
                return specTwo;
            }

            public void setSpecTwo(int specTwo) {
                this.specTwo = specTwo;
            }

            public List<DatalistBean> getDatalist() {
                return datalist;
            }

            public void setDatalist(List<DatalistBean> datalist) {
                this.datalist = datalist;
            }

            public List<?> getInfolist() {
                return infolist;
            }

            public void setInfolist(List<?> infolist) {
                this.infolist = infolist;
            }

            public static class DatalistBean {
                /**
                 * picUrl : /upload/20181011/6cbb675d-8abe-4fdb-8454-1af0faa21fed.jpg
                 * money : 0.01
                 * stock : 100
                 */

                private String picUrl;
                private double money;
                private int stock;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public double getMoney() {
                    return money;
                }

                public void setMoney(double money) {
                    this.money = money;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }
            }
        }
    }

    /*
    *//**
     * result : 1
     * msg : 获取成功!
     * data : [{"specName":"规格","id":4,"itemlist":[{"specOne":3,"datalist":[{"picUrl":"/upload/20180822/43074388-9a07-4ff3-87d0-9be94a52ff94.jpg","money":199,"stock":100}],"name":"2L","specTwo":0,"infolist":[]},{"specOne":4,"datalist":[{"picUrl":"/upload/20180822/ad00f7d9-51aa-4af9-a9b8-00b08030cf48.jpg","money":399,"stock":50}],"name":"3L","specTwo":0,"infolist":[]},{"specOne":5,"datalist":[{"picUrl":"/upload/20180822/6c65076a-8eff-4f34-afb7-6dde54028263.jpg","money":499,"stock":88}],"name":"3.5L","specTwo":0,"infolist":[]}]}]
     *//*

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
        *//**
         * specName : 规格
         * id : 4
         * itemlist : [{"specOne":3,"datalist":[{"picUrl":"/upload/20180822/43074388-9a07-4ff3-87d0-9be94a52ff94.jpg","money":199,"stock":100}],"name":"2L","specTwo":0,"infolist":[]},{"specOne":4,"datalist":[{"picUrl":"/upload/20180822/ad00f7d9-51aa-4af9-a9b8-00b08030cf48.jpg","money":399,"stock":50}],"name":"3L","specTwo":0,"infolist":[]},{"specOne":5,"datalist":[{"picUrl":"/upload/20180822/6c65076a-8eff-4f34-afb7-6dde54028263.jpg","money":499,"stock":88}],"name":"3.5L","specTwo":0,"infolist":[]}]
         *//*

        private String specName;
        private int id;
        private List<ItemlistBean> itemlist;

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
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
            *//**
             * specOne : 3
             * datalist : [{"picUrl":"/upload/20180822/43074388-9a07-4ff3-87d0-9be94a52ff94.jpg","money":199,"stock":100}]
             * name : 2L
             * specTwo : 0
             * infolist : []
             *//*

            private int specOne;
            private String name;
            private int specTwo;
            private List<DatalistBean> datalist;
            private List<?> infolist;

            public int getSpecOne() {
                return specOne;
            }

            public void setSpecOne(int specOne) {
                this.specOne = specOne;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSpecTwo() {
                return specTwo;
            }

            public void setSpecTwo(int specTwo) {
                this.specTwo = specTwo;
            }

            public List<DatalistBean> getDatalist() {
                return datalist;
            }

            public void setDatalist(List<DatalistBean> datalist) {
                this.datalist = datalist;
            }

            public List<?> getInfolist() {
                return infolist;
            }

            public void setInfolist(List<?> infolist) {
                this.infolist = infolist;
            }

            public static class DatalistBean {
                *//**
                 * picUrl : /upload/20180822/43074388-9a07-4ff3-87d0-9be94a52ff94.jpg
                 * money : 199
                 * stock : 100
                 *//*

                private String picUrl;
                private double money;
                private int stock;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public double getMoney() {
                    return money;
                }

                public void setMoney(int money) {
                    this.money = money;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }
            }
        }
    }*/

}
