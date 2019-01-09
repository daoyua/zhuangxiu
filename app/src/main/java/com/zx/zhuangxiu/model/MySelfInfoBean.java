package com.zx.zhuangxiu.model;

public class MySelfInfoBean {


        private int result;
        private String msg;
        private Data data;
        public void setResult(int result) {
            this.result = result;
        }
        public int getResult() {
            return result;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }

        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }
    public class Data {

        private String address;
        private String longitude;
        private String latitude;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
