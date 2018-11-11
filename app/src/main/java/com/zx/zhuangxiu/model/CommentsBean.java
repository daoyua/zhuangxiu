package com.zx.zhuangxiu.model;

import java.util.List;

public class CommentsBean {

    /**
     * result : 1
     * msg : 获取成功!
     * data : [{"parent":{"dataId":7,"addTime":"2018-09-21 13:43:11","nickname":"流量卡","id":11,"type":0,"parentId":0,"content":"推荐","userUrl":"/upload/20180921/90791ea8-8a1b-41e7-9eae-5faa6ebf65c1.png"},"child":[]},{"parent":{"dataId":7,"addTime":"2018-09-21 13:44:17","nickname":"流量卡","id":12,"type":0,"parentId":0,"content":"啦咯啦咯啦咯啦","userUrl":"/upload/20180921/90791ea8-8a1b-41e7-9eae-5faa6ebf65c1.png"},"child":[]}]
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
         * parent : {"dataId":7,"addTime":"2018-09-21 13:43:11","nickname":"流量卡","id":11,"type":0,"parentId":0,"content":"推荐","userUrl":"/upload/20180921/90791ea8-8a1b-41e7-9eae-5faa6ebf65c1.png"}
         * child : []
         */

        private ParentBean parent;
        private List<?> child;

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
            this.parent = parent;
        }

        public List<?> getChild() {
            return child;
        }

        public void setChild(List<?> child) {
            this.child = child;
        }

        public static class ParentBean {
            /**
             * dataId : 7
             * addTime : 2018-09-21 13:43:11
             * nickname : 流量卡
             * id : 11
             * type : 0
             * parentId : 0
             * content : 推荐
             * userUrl : /upload/20180921/90791ea8-8a1b-41e7-9eae-5faa6ebf65c1.png
             */

            private int dataId;
            private String addTime;
            private String nickname;
            private int id;
            private int type;
            private int parentId;
            private String content;
            private String userUrl;

            public int getDataId() {
                return dataId;
            }

            public void setDataId(int dataId) {
                this.dataId = dataId;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUserUrl() {
                return userUrl;
            }

            public void setUserUrl(String userUrl) {
                this.userUrl = userUrl;
            }
        }
    }
}
