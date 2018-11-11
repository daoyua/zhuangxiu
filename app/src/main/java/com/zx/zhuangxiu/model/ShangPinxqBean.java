package com.zx.zhuangxiu.model;

public class ShangPinxqBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : {"broadcastUrl":"/upload/20180828/832e62a9-e3d8-44ff-a391-d67a32caf453.jpg,/upload/20180828/66cbf107-badc-4d1a-ab90-c48f1bbbdf97.jpg,","totalID":5,"detailMsg":"/upload/20180828/2989edc2-e2b6-4df3-9b5d-440e5a8f3e2d.JPG,","pin":0,"surplusCount":1000,"noticeMsg":"<img src=\"/upload/20180828/4f9f1815-9352-4720-a7ef-3afb862b59b1.JPG\" alt=\"undefined\">","price":123,"cdname":"阳台小公园","simple":"Asdasd","typeId":5,"id":1,"serverContent":null}
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
         * broadcastUrl : /upload/20180828/832e62a9-e3d8-44ff-a391-d67a32caf453.jpg,/upload/20180828/66cbf107-badc-4d1a-ab90-c48f1bbbdf97.jpg,
         * totalID : 5
         * detailMsg : /upload/20180828/2989edc2-e2b6-4df3-9b5d-440e5a8f3e2d.JPG,
         * pin : 0
         * surplusCount : 1000
         * noticeMsg : <img src="/upload/20180828/4f9f1815-9352-4720-a7ef-3afb862b59b1.JPG" alt="undefined">
         * price : 123
         * cdname : 阳台小公园
         * simple : Asdasd
         * typeId : 5
         * id : 1
         * serverContent : null
         */

        private String broadcastUrl;
        private int totalID;
        private String detailMsg;
        private int pin;
        private int surplusCount;
        private String noticeMsg;
        private double price;
        private String cdname;
        private String simple;
        private int typeId;
        private int id;
        private Object serverContent;

        public String getBroadcastUrl() {
            return broadcastUrl;
        }

        public void setBroadcastUrl(String broadcastUrl) {
            this.broadcastUrl = broadcastUrl;
        }

        public int getTotalID() {
            return totalID;
        }

        public void setTotalID(int totalID) {
            this.totalID = totalID;
        }

        public String getDetailMsg() {
            return detailMsg;
        }

        public void setDetailMsg(String detailMsg) {
            this.detailMsg = detailMsg;
        }

        public int getPin() {
            return pin;
        }

        public void setPin(int pin) {
            this.pin = pin;
        }

        public int getSurplusCount() {
            return surplusCount;
        }

        public void setSurplusCount(int surplusCount) {
            this.surplusCount = surplusCount;
        }

        public String getNoticeMsg() {
            return noticeMsg;
        }

        public void setNoticeMsg(String noticeMsg) {
            this.noticeMsg = noticeMsg;
        }

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

        public String getSimple() {
            return simple;
        }

        public void setSimple(String simple) {
            this.simple = simple;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getServerContent() {
            return serverContent;
        }

        public void setServerContent(Object serverContent) {
            this.serverContent = serverContent;
        }
    }
}
