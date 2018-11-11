package com.zx.zhuangxiu.model;

import java.util.List;

public class MyDongTaiGR {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"imgUrl":"","address":"河北省石家庄市长安区青园街","name":"测试","id":1},{"imgUrl":"","address":"河北省石家庄市长安区青园街","name":"测试","id":3},{"imgUrl":"","address":"河北省石家庄市长安区青园街","name":"测试","id":4},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":1},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":2},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":3},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":4},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":5},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":6},{"imgUrl":"/upload/20180822/f04b745e-7ccd-4fad-b328-44a06c6983a3.jpg","address":"2","name":"2","id":7}]
     * totalCount : 8
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
         * imgUrl :
         * address : 河北省石家庄市长安区青园街
         * name : 测试
         * id : 1
         */

        private String imgUrl;
        private String address;
        private String name;
        private int id;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
