package com.zx.zhuangxiu.model;

import java.util.List;

public class ToutiaoBena {


    /**
     * result : 1
     * msg : 查询成功！！
     * data : [{"img":"/upload/20180929/2fe6148f-0117-4be2-9a9d-b593c2896e42.jpg","id":"3","title":"奥斯卡级汇顶科技爱神的箭看撒谎","info":"砂带回家看撒谎的痕迹啥客户端卡是开奖汇顶科技啥活动阖家安康山"},{"img":"/upload/20180929/6fcf41d2-687e-4671-a54c-e71cc3715067.jpg","id":"2","title":"的接口拉丝机代课老师","info":"打算的撒很快就的好时机ad进口红酒哈市将肯定会看撒娇回到家安"},{"img":"/upload/20180929/6e017e74-4862-49d0-ba65-50f087af5280.jpg","id":"1","title":"单电大厦电脑上","info":"打开了萨克的拉萨佳都科技萨拉就登记了撒旦教会计师卡了解到就卡"}]
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
         * img : /upload/20180929/2fe6148f-0117-4be2-9a9d-b593c2896e42.jpg
         * id : 3
         * title : 奥斯卡级汇顶科技爱神的箭看撒谎
         * info : 砂带回家看撒谎的痕迹啥客户端卡是开奖汇顶科技啥活动阖家安康山
         */

        private String img;
        private int id;
        private String title;
        private String info;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}