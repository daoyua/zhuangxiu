package com.zx.zhuangxiu.model;

import java.util.List;

public class FindService {

    /**
     * result : 1
     * msg : 获取成功!
     * data : {"page":{"keyWord":null,"pageSize":null,"pageNow":null,"total":1,"totalPage":null,"startRow":0,"endRow":1,"pageContent":[{"imgUrl":"/upload/20180829/709f3b78-e3f1-468e-b939-1b1edf6c7784.png","address":"石家庄","addTime":"2018-08-29 17:39:41","isalone":0,"isgood":0,"name":"寻找专业设计团队","id":14,"thumbsup":0}]},"classList":[{"cname":"玉石材料","id":7}]}
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
         * page : {"keyWord":null,"pageSize":null,"pageNow":null,"total":1,"totalPage":null,"startRow":0,"endRow":1,"pageContent":[{"imgUrl":"/upload/20180829/709f3b78-e3f1-468e-b939-1b1edf6c7784.png","address":"石家庄","addTime":"2018-08-29 17:39:41","isalone":0,"isgood":0,"name":"寻找专业设计团队","id":14,"thumbsup":0}]}
         * classList : [{"cname":"玉石材料","id":7}]
         */

        private PageBean page;
        private List<ClassListBean> classList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ClassListBean> getClassList() {
            return classList;
        }

        public void setClassList(List<ClassListBean> classList) {
            this.classList = classList;
        }

        public static class PageBean {
            /**
             * keyWord : null
             * pageSize : null
             * pageNow : null
             * total : 1
             * totalPage : null
             * startRow : 0
             * endRow : 1
             * pageContent : [{"imgUrl":"/upload/20180829/709f3b78-e3f1-468e-b939-1b1edf6c7784.png","address":"石家庄","addTime":"2018-08-29 17:39:41","isalone":0,"isgood":0,"name":"寻找专业设计团队","id":14,"thumbsup":0}]
             */

            private Object keyWord;
            private Object pageSize;
            private Object pageNow;
            private int total;
            private Object totalPage;
            private int startRow;
            private int endRow;
            private List<PageContentBean> pageContent;

            public Object getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(Object keyWord) {
                this.keyWord = keyWord;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }

            public Object getPageNow() {
                return pageNow;
            }

            public void setPageNow(Object pageNow) {
                this.pageNow = pageNow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public Object getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Object totalPage) {
                this.totalPage = totalPage;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public List<PageContentBean> getPageContent() {
                return pageContent;
            }

            public void setPageContent(List<PageContentBean> pageContent) {
                this.pageContent = pageContent;
            }

            public static class PageContentBean {
                /**
                 * imgUrl : /upload/20180829/709f3b78-e3f1-468e-b939-1b1edf6c7784.png
                 * address : 石家庄
                 * addTime : 2018-08-29 17:39:41
                 * isalone : 0
                 * isgood : 0
                 * name : 寻找专业设计团队
                 * id : 14
                 * thumbsup : 0
                 */

                private String imgUrl;
                private String address;
                private String addTime;
                private int isalone;
                private int isgood;
                private String name;
                private int id;
                private int thumbsup;

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

                public String getAddTime() {
                    return addTime;
                }

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public int getIsalone() {
                    return isalone;
                }

                public void setIsalone(int isalone) {
                    this.isalone = isalone;
                }

                public int getIsgood() {
                    return isgood;
                }

                public void setIsgood(int isgood) {
                    this.isgood = isgood;
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

                public int getThumbsup() {
                    return thumbsup;
                }

                public void setThumbsup(int thumbsup) {
                    this.thumbsup = thumbsup;
                }
            }
        }

        public static class ClassListBean {
            /**
             * cname : 玉石材料
             * id : 7
             */

            private String cname;
            private int id;

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
