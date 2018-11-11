package com.zx.zhuangxiu.model;

import java.util.List;

public class BusinessCgspBean {


    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"img":"/upload/20180829/74be56fa-b120-42e0-84ff-0ddbdfa76f12.jpg","phone":53,"price":55,"num":55,"creattime":1535522539000,"endtime":1534262400000,"goodsname":"424","id":2,"isdelete":0,"goodsinfo":"4254","userId":1},{"img":"/upload/20180829/be9a1a5e-f69e-470d-b13d-33c741e48b73.jpg","phone":12,"price":11,"num":11,"creattime":1535522532000,"endtime":1533830400000,"goodsname":"舒服","id":1,"isdelete":0,"goodsinfo":"爱迪生","userId":1}]
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
         * img : /upload/20180829/74be56fa-b120-42e0-84ff-0ddbdfa76f12.jpg
         * phone : 53
         * price : 55
         * num : 55
         * creattime : 1535522539000
         * endtime : 1534262400000
         * goodsname : 424
         * id : 2
         * isdelete : 0
         * goodsinfo : 4254
         * userId : 1
         */

        private String img;
        private long phone;
        private double price;
        private int num;
        private long creattime;
        private long endtime;
        private String goodsname;
        private int id;
        private int isdelete;
        private String goodsinfo;
        private int userId;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public long getCreattime() {
            return creattime;
        }

        public void setCreattime(long creattime) {
            this.creattime = creattime;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public String getGoodsname() {
            return goodsname;
        }

        public void setGoodsname(String goodsname) {
            this.goodsname = goodsname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getGoodsinfo() {
            return goodsinfo;
        }

        public void setGoodsinfo(String goodsinfo) {
            this.goodsinfo = goodsinfo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
