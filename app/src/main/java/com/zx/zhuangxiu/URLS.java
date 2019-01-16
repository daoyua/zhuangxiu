package com.zx.zhuangxiu;

import com.zx.zhuangxiu.model.SubmitDdTwo;

public class URLS {

//    public static final String HTTP = "http://47.75.53.109:9095";//测试
    public static final String HTTP = "http://47.93.215.205:9095";//正式
//        public static final String HTTP = "http://192.168.2.141:9095";
    public static final String BANNER = HTTP+"/api/homepage/sowingList";
//    public static final String BANNER = HTTP+"http://47.93.215.205:9095/api/homepage/sowingList";
    public static int user_id;
    public static int address;
    public static String location;
    public static final String KEFU = "https://kefu.easemob.com/webim/im.html?configId=109e72a8-6753-44db-9ad6-a916da0bc958";
//    public static final String KEFU = "http://chat.weiliaokefu.com/mobile-index-cid-27518";
    public static String YOUR;

    public static String getYOUR() {
        return YOUR;
    }

    public static void setYOUR(String YOUR) {
        URLS.YOUR = YOUR;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        URLS.user_id = user_id;
    }

    public static int getAddress() {
        return address;
    }

    public static void setAddress(int address) {
        URLS.address = address;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        URLS.location = location;
    }
    ///////////////////////////////////////////////登陆模块接口//////////////////////////////////////////////

    public static String Firstlogin(String telenumber, String code) {
        String getyz = HTTP + "/api/login/phonelogin?telenumber=" + telenumber + "&code=" + code;
        return getyz;
    }

    public static String Firstlogin(String telenumber, String code, String openID, String token) {
        String getyz = HTTP + "/api/login/phonelogin?telenumber=" + telenumber + "&code=" + code + "&openId=" + openID + "&access_token=" + token;
        return getyz;
    }


    /**
     * 上传图片
     * http://localhost:8081/zxWeb/photo/add
     * pkId：用户ID
     */
    public static String toimage() {
        String getyz = HTTP + "/api/homepage/imgUpload";
        return getyz;
    }

    /* 上传头像*/
    public static String modify() {
        String getyz = HTTP + "/api/Personal/updateUserUrl";
        return getyz;
    }

    /**
     * 我的信息
     * http://47.93.215.205:8081/zxWeb/user/findUserInfo?pkId=3
     * pkId：用户ID
     */
    public static String mydata(int pkId) {
        String getyz = HTTP + "/api/Personal/MyselfMsg?userId=" + pkId;
        //  String getyz = "http://192.168.1.8:8081/zxWeb/user/findUserInfo?pkId=" + pkId;
        return getyz;
    }

    /**
     * 我的-我的动态（个人）
     * http://47.93.215.205:8081/zxWeb/personOrder/findPersonServiceOrderById?userId=3
     */
    public static String mydongtaigeren(int userID) {
        String getyz = HTTP + "/api/Personal/MyDynamics?userId=" + userID;
        return getyz;
    }

    /**
     * 我的-我的动态（企业）
     * http://47.93.215.205:8081/zxWeb/findServe/findMyService?userId=2
     */
    public static String mydongqiye(int userID) {
        String getyz = HTTP + "/api/Personal/MyDynamics?userId=" + userID;
        return getyz;
    }

    /**
     * 我的-我的动态（商家）
     * http://47.93.215.205:8081/zxWeb/findProduct/findProductDetilById?pkId=3
     */
    public static String mydongshangjia(int pkId) {
        String getyz = HTTP + "/zxWeb/findProduct/findMyProduct?userId=" + pkId;
        return getyz;
    }

    /**
     * 我的-积分商城（只有个人端才有）
     * http://47.93.215.205:8081/zxWeb/personMarket/selectPersonMarket
     */
    public static String jifenshangcheng() {
        String getyz = HTTP + "/api/product/MyShop";
        return getyz;
    }


    /**
     * 我的-我的订单（个人）
     * http://47.93.215.205:8081/zxWeb/personOrder/findPersonOrderById?userId=3&status=0
     */
    public static String mydingdangeren(int userId, String status) {
        String getyz = HTTP + "/zxWeb/personOrder/findPersonOrderById?userId=" + userId + "&status=" + status;
        return getyz;
    }

    public static String mydingdanqiye() {
        String getyz = HTTP + "/api/order/MyService?userId=1";
        return getyz;
    }

    public static String MyOrder(int user_id, int orderstatus) {
        String getyz = HTTP + "/api/order/MyOrder?userId=" + user_id + "&orderstatus=" + orderstatus;
        return getyz;
    }


    /**
     * 我的-我的订单-订单详情服务-工种（个人）
     * http://47.93.215.205:8081/zxWeb/personOrder/findWorkTypeByRecordId?recordId=5
     */
    public static String mydingdangerenxiangqingfuwugongzhong(int recordId) {
        String getyz = HTTP + "/zxWeb/personOrder/findWorkTypeByRecordId?recordId=" + recordId;
        return getyz;
    }

    /**
     * 我的-我的订单-订单详情服务-信息（个人）
     * http://47.93.215.205:8081/zxWeb/personOrder/findServiceOrderDetailByRecordId?recordId=5
     */
    public static String mydingdangerenxiangqingfuwuxinxi(int recordId) {
        String getyz = HTTP + "/zxWeb/personOrder/findServiceOrderDetailByRecordId?recordId=" + recordId;
        return getyz;
    }

    /**
     * 我的-我的信息-更改昵称
     * http://47.93.215.205:8081/zxWeb/user/updateuser?pkId=3&nickName=23
     */
    public static String changename(int pkid, String nickName) {
        String getyz = HTTP + "/zxWeb/user/updateuser?pkId=" + pkid + "&nickName=" + nickName;
        return getyz;
    }

    /**
     * 修改昵称s
     */
    public static String changenames(int pkid, String nickName) {
        String getyz = HTTP + "/api/Personal/modify?" + "userId=" + pkid + "&nickname=" + nickName;
        return getyz;
    }

    /**
     * 获取手机号验证码
     * http://47.93.215.205:8081/zxWeb/smsverification?phone=15903363390
     */
    public static String getphonecode(String telenumber) {
        String getyz = HTTP + "/api/login/SendCode?telenumber=" + telenumber;
        return getyz;
    }

    /**
     * 我的-我的信息-更改手机号
     * http://47.93.215.205:8081/zxWeb/user/updateuser?pkId=3&mobile=23
     */
    public static String changephone(int pkid, String telenumber, String code) {
        String getyz = HTTP + "/api/Personal/binding?userId=" + pkid + "&telenumber =" + telenumber + "&code" + code;
        return getyz;
    }

    /**
     * 支付宝账号
     */
    public static String tijiaozhifubao(int pkid, String accountNo, String accountNos) {
        String getyz = HTTP + "/api/Personal/HelpAlipay?userId=" + pkid + "&accountNo=" + accountNo + "&accountNos=" + accountNos;
        return getyz;
    }


    //===================================2018年7月27日19:11:48xkk======================================

    /**
     * 我的-我的信息-更改头像
     * http://47.93.215.205:8081/zxWeb/user/updateuser?pkId=3&headPhoto=23
     */
    public static String changetouxiang(int pkid, int imgeid) {
        String getyz = HTTP + "/api/Personal/updateUserUrl?userId =" + pkid + "&headPhoto=" + imgeid;
        // String getyz="http://192.168.1.16:8081/zxWeb/user/updateuser?pkId="+pkid+"&headPhoto="+imgeid;
        return getyz;
    }

    /**
     * -------------------------------------------------------首页接口-----------------------------------------------------------------
     * 首页轮播图
     * http://47.93.215.205:8081/zxWeb/troll/selectAll
     */
    public static String lunBoShow() {
        String getyz = HTTP + "/zxWeb/troll/selectAll";
        return getyz;
    }


    /*
     * 首页商品推荐
     *  http://47.93.215.205:8081/zxWeb//findProduct/findRecommendProduct
     * */
    public static String shangPinTuiJianShow() {
        String getyz = HTTP + "/api/homepage/selectcommodity?type=0";
        return getyz;
    }


    /*
     * 首页找工人
     *  http://47.93.215.205:8081/zxWeb/findWorker/selectWorker?pkId=3&findType=1
     * */
    public static String syFoundWorkerShow(int typeId) {
        String getyz = HTTP + "/api/LookWork/most?type=" + typeId+"&userId="+getUser_id();
        return getyz;
    }

    public static String syFoundWorkerShow(int typeId, String address) {
        String getyz = HTTP + "/api/LookWork/most?type=" + typeId + "&Location=" + address;
        return getyz;
    }

    /*
     * 首页找工人-详情
     *  http://47.93.215.205:8081/zxWeb/findWorker/findWorkerDetailById?pkId=3
     * */
    public static String syFoundWorkerDetailsShow(int pkId) {
        String getyz = HTTP + "/api/LookWork/GetUserDetail?userId=" + pkId;
        return getyz;
    }

    /*
     * 首页找工作-----发现找工作
     *  http://47.93.215.205:8081/zxWeb/findWork/selectWork
     * */
    public static String syFoundWorkShow() {
        String getyz = HTTP + "/api/job/GetJoblist?userId="+getUser_id();
        return getyz;
    }

    public static String syFoundWorkShow(String searchName, String city) {
        String getyz = HTTP + "/api/job/GetJoblist?searchName=" + searchName + "&city=" + city+"&userId="+getUser_id();
        return getyz;
    }

    /*
     * 首页找工作详情
     *  http://47.93.215.205:8081/zxWeb/findWork/findWorkDetilById?pkId=5
     * */
    public static String syFoundWorkDetailsShow(int jobId) {
        String getyz = HTTP + "/api/job/GetWorkDetail?jobId=" + jobId;
        return getyz;
    }

    /////////////////////////////////////////////////商机页面三大模块的三个接口地址//////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
     * 商机页面采购商品接口
     *   http://47.93.215.205:8081/zxWeb/findProduct/selectProduct?type=1
     * */
    public static String shangjiChanPinShow(int type) {
        String getyz = HTTP + "/api/business/selectpurchase?type=0"+"&userId="+getUser_id();
//        String getyz = "http://192.168.1.8:8081/zxWeb/findProduct/selectProduct?type="+type;
        return getyz;
    }

    /**
     * 商机页面购买服务接口
     *
     * @return
     * @para
     */
    public static String goumaifuwuShow() {
        String getyz = HTTP + "/api/business/selectpurchase?type=1&userId="+getUser_id();
        return getyz;
    }

    /*
     * 商机-加盟合作
     * http://47.93.215.205:8081/zxWeb/findWorker/findShop
     * */
    public static String sjJmhzShow() {
        String getyz = HTTP + "/api/business/selectpurchase?type=2"+"&userId="+getUser_id();
//        String getyz ="http://192.168.1.8:8081/zxWeb/findWorker/findShop";
        return getyz;
    }


    /**
     * 首页找产拼接口
     *
     * @param type
     * @return
     */

    public static String syChanPinShow(int type) {
        String getyz = HTTP + "/api/product/GetProductlistByTypeId?typeId=" + type+"&userId="+getUser_id();
//        String getyz = "http://192.168.1.8:8081/zxWeb/findProduct/selectProduct?type="+type;
        return getyz;
    }

    /**
     * 发现找服务
     *
     * @returns
     */
    public static String syChanPinShows(int type) {
        String getyz = HTTP + "/api/product/GetProductlistByTypeId?userId=" + getUser_id();
//        String getyz = "http://192.168.1.8:8081/zxWeb/findProduct/selectProduct?type="+type;
        return getyz;
    }


    /*
     * 首页找产品与直接进入点赞接口
     *   http://47.93.215.205:8081/zxWeb/personPraise/insertPersonPraise?userId=3&recordId=1&Type=2
     * */
    public static String syChanPinZanShow(int userId, int recordId, int type) {
        String getyz = HTTP + "/zxWeb/personPraise/insertPersonPraise?userId=" + userId + "&recordId=" + recordId + "&Type=" + type;
        return getyz;
    }

    /*
     * 首页找服务:serviceSign:1---普通；2----金牌服务
     * http://47.93.215.205:8081/zxWeb/findServe/selectService?type=3&serviceSign=2
     * */
    public static String syFuWuShow(int type) {
        String getyz = HTTP + "/api/server/serverList?classId=" + type+"&userId="+getUser_id();
        return getyz;
    }


    /*
     * 首页找服务详情接口
     * http://47.93.215.205:8081/zxWeb/findServe/findServiceDetail?pkId=1
     * */
    public static String syFuWuDetailsShow(int serverId) {
        String getyz = HTTP + "/api/server/getServer?serverId=" + serverId;
//        String getyz = "http://192.168.1.8:8081/zxWeb/findServe/findServiceDetail?pkId="+pkId;
        return getyz;
    }

    /*
     * 发现找服务
     * http://47.93.215.205:8081/zxWeb/findServe/selectService?type=0&serviceSign=1
     * */
    public static String fxFuWuShow() {
        String getyz = HTTP + "/api/server/serverList";
        return getyz;
    }


    /*
     * 首页金牌服务
     *   http://47.93.215.205:8081/zxWeb/goldServe/findGoldServe
     * */
    public static String syJinPaiFuWuShow() {
        String getyz = HTTP + "/api/homepage/selectcommodity?type=2";
        return getyz;
    }

    /**
     * 首页===直接进入
     *
     * @return
     */
    public static String zhijiejinru() {
        String getyz = HTTP + "/api/homepage/selectcommodity?type=1";
        return getyz;
    }

    /**
     * 私人定制服务
     *
     * @return
     */
    public static String sysrdzFuWuShow() {
        String getyz = HTTP + "/api/homepage/selectcommodity?type=3";
        return getyz;
    }

    /*
     * 发现-找工人
     * http://47.93.215.205:8081/zxWeb/findWorker/selectWorker?pkId=3&findType=0
     * */
    public static String fxFoundWorkerShow(int userId, int findType) {
        String getyz = HTTP + "/api/LookWork/most";
        return getyz;
    }


    /*
     * 首页-搜索（产品）
     * http://47.93.215.205:8081/zxWeb/search/findByTitle?title=%E7%8E%89%E7%9F%B3
     * */
    public static String sySearchShow(String title) {
        String getyz = HTTP + "/api/homepage/selectName?cdname=" + title;
//        String getyz = "http://192.168.1.8:8081/zxWeb/search/findByTitle?title="+title;
        return getyz;
    }


    /*
     * 首页-找工人-搜索
     * http://47.93.215.205:8081/zxWeb/findWorker/searchWorkerByNameAndWorkType?userName=时&workName
     * */
    public static String syZgrSearchShow(String userName) {
        String getyz = HTTP + "/api/LookWork/most?seachName=" + userName;
//        String getyz = "http://192.168.1.8:8081/zxWeb/findWorker/searchWorkerByNameAndWorkType?userName="+userName;
        return getyz;
    }

    /*
     * 首页-找服务-搜索
     * http://47.93.215.205:8081/zxWeb/findServe/searchServiceByTitle?title=
     * */
    public static String syFwSearchShow(String title) {
        String getyz = HTTP + "/api/server/serverList?seachName=" + title;
//        String getyz = "http://192.168.1.8:8081/zxWeb/findServe/searchServiceByTitle?title="+title;
        return getyz;
    }


    /*
     * 首页-找工作-搜索
     * http://47.93.215.205:8081/zxWeb/findWork/findWorkByRequire?address=%E5%8C%97%E4%BA%AC&title=XF
     * */
    public static String syZgzSearchShow(String address, String title) {
        String getyz = HTTP + "/zxWeb/findWork/findWorkByRequire?address=" + address + "&title=" + title;
        return getyz;
    }


    /*
     * 商品详情
     * http://192.168.1.8:8081/zxWeb/findProduct/findProductDetailById?pkId=1
     * */
    public static String detailsShow(int pkId) {
        String getyz = HTTP + "/api/product/GetProductDetailById?commodityId=" + pkId;
//        String getyz ="http://192.168.1.8:8081/zxWeb/findProduct/findProductDetailById?pkId="+pkId;
        return getyz;
    }

    /*
     * 添加评论的接口：type:1:服务；2:产品3.j积分4.用户5.优质项目
     * http://192.168.1.19:8081/zxWeb/comment/saveComment?userId=3&recordId=1&type=3&commentType=1&content=挺好的
     * */
    public static String addPingLunShow(int userId, int recordId, int type, int commentType, String content) {
        String getyz = HTTP + "/zxWeb/comment/saveComment?userId=" + userId + "&recordId=" + recordId + "&type=" + type + "&commentType=" + commentType + "&content=" + content;
//        String getyz ="http://192.168.1.19:8081/zxWeb/comment/saveComment?userId="+userId+"&recordId="+recordId+"&type="+type+"&commentType="+commentType+"&content="+content;
        return getyz;
    }


    /*
     * 产品评论的列表接口：type:1:服务；2:产品3.j积分4.用户5.优质项目
     *  http://192.168.1.19:8081/zxWeb/comment/comment?recordId=4&type=2
     * */
    public static String pingLunShow(int type, int id) {
        String getyz = HTTP + "/api/server/getCommentList?type=" + type + "&id=" + id;
//        String getyz ="http://192.168.1.19:8081/zxWeb/comment/comment?recordId="+recordId+"&type=2";
        return getyz;
    }


    /*
     * 首页-消息-系统消息
     *   http://192.168.1.19:8081/zxWeb/news/findBySystem
     * */
    public static String xiTongShow() {
        String getyz = HTTP + "/api/apinews/newsList?type=0";
//        String getyz ="http://192.168.1.19:8081/zxWeb/news/findBySystem";
        return getyz;
    }

    /*
     * 首页-消息-优惠通知
     *   http://192.168.1.19:8081/zxWeb/news/findBytype?userId=1
     * */
    public static String youhuiShow() {
        String getyz = HTTP + "/api/apinews/newsList?type=1";
//        String getyz ="http://192.168.1.19:8081/zxWeb/news/findBytype?userId="+userId;
        return getyz;
    }

    /**
     * 首页====消息之活动专题推送
     *
     * @return
     */


    /*
     * 首页-大时圈头条
     *   http://192.168.1.8:8081/zxWeb/daShiQuan/findTopNews
     * */
    public static String daShiQuanShow() {
        String getyz = HTTP + "/api/homepage/selectheadlineList";
//        String getyz ="http://192.168.1.8:8081/zxWeb/daShiQuan/findTopNews";
        return getyz;
    }


    /*
     * 首页-大时圈头条-详情
     *   http://192.168.1.8:8081/zxWeb/daShiQuan//findTopNewsDetailById?pkId=1
     * */
    public static String daShiQuanDetailsShow(int id) {
        String getyz = HTTP + "/api/homepage/selectoneheadline?id=" + id;
//        String getyz ="http://192.168.1.8:8081/zxWeb/daShiQuan//findTopNewsDetailById?pkId="+pkId;
        return getyz;
    }


    /*
     * 详情-直接购买
     *    http://192.168.1.16:8081/zxWeb/personOrder/saveorder?type=2&recordId=1&num=1&userId=3
     * */
    public static String daShiQuanDetailsShow(int userId, int type, int recordId, int num) {
        String getyz = HTTP + "/zxWeb/personOrder/saveorder?userId=" + userId + "&type=" + type + "&recordId=" + recordId + "&num=" + num;
//        String getyz ="http://192.168.1.16:8081/zxWeb/personOrder/saveorder?userId="+userId+"&type="+type+"&recordId="+recordId+"&num="+num;
        return getyz;
    }


    /*
     * 支付宝购买
     *   http://192.168.1.16:8081/zxWeb/alipay/pay?orderId=180730195005371658
     * */
    public static String zhiFuBaoShow(String orderId) {
        String getyz = HTTP + "/zxWeb/alipay/pay?orderId=" + orderId;
//        String getyz ="http://192.168.1.16:8081/zxWeb/alipay/pay?orderId="+orderId;
        return getyz;
    }

    /*
     * 微信购买
     *    http://192.168.1.16:8081/zxWeb/wxpay/wxpay?orderId=180730195005371658
     * */
    public static String weiXinShow(String orderId) {
        String getyz = HTTP + "/zxWeb/wxpay/wxpay?orderId=" + orderId;
//       String getyz ="http://192.168.1.16:8081/zxWeb/wxpay/wxpay?orderId="+orderId;
        return getyz;
    }

    /*
     * 发布工作--添加工种
     *  http://192.168.1.8:8081/zxWeb/insert/insertWorkType?workName=%E6%B2%B9%E6%BC%86%EF%BC%8C%E6%B0%B4%E7%94%B5
     * */
    public static String addGongZhongShow(String workName, int user_id) {
        String getyz = HTTP + "/zxWeb/insert/insertWorkType?workName=" + workName + "&createId=" + user_id;
//        String getyz ="http://192.168.1.8:8081/zxWeb/insert/insertWorkType?workName="+workName;
        return getyz;
    }


    /*
     * 发布工作-生成服务Id
     * http://192.168.1.8:8081/zxWeb/insert/insertService?title=%E5%BB%BA%E7%AD%91%E6%88%BF%E5%B1%8B
     * &address=%E6%B2%B3%E5%8C%97%E7%9C%81%E9%82%AF%E9%83%B8%E5%B8%82%E5%AE%B6%E5%85%B7%E6%94%B6%E8%B4%A7
     * &area=10000&photo=5&startDate=2018-8-18&endDate=2018-8-30&workId=1&remark=%E5%8A%A0%E5%BF%AB%E5%86%B3%E5%AE%9A
     * */
    public static String SaveJob() {
        String getyz = HTTP + "/api/job/SaveJob";
        return getyz;
    }


    public static String faBuOneShow(String summary, String content, String title, String address, String area, String photo, int workId, String remark, String startDate, String endDate, String serviceType, int createId) {
        String getyz = HTTP + "/zxWeb/insert/insertService?title=" + title + "&address=" + address + "&area=" + area + "&photo=" + photo
                + "&workId=" + workId + "&remark=" + remark + "&startDate=" + startDate + "&endDate=" + endDate + "&serviceType=" + serviceType + "&createId=" + createId
                + "&summary=" + summary + "&content=" + content;
//        String getyz ="http://192.168.1.8:8081/zxWeb/insert/insertService?title="+title+"&address="+address+"&area="+area+"&photo="+photo
//                +"&startDate="+startDate+"&endDate="+endDate+"&workId="+workId+"&remark="+remark;
        return getyz;
    }


    /*
     * 发布工作-生成服务Id
     * http://47.93.215.205:8081/zxWeb/insert/insertServiceWork?workId=1&serviceId=1
     * */
    public static String faBuTwoShow(int workId, int serviceId, String reqNum) {
        String getyz = HTTP + "/zxWeb/insert/insertServiceWork?workId=" + workId + "&serviceId=" + serviceId + "&reqNum=" + reqNum;
//        String getyz ="http://192.168.1.8:8081/zxWeb/insert/insertWorkType?workId="+workId+"&serviceId="+serviceId;
        return getyz;
    }


    /**
     * -------------------------------购物车相关-------------------------------------------------------------------------------------
     **/
    /*
     * 添加购物车
     * http://47.93.215.205:8081/zxWeb/shop/add?userId=3&recordId=1&type=1&payNum=1&recordPhoto=
     * */
    public static String addShopShow() {
        String getyz = HTTP + "/api/shopCart/addShopCart";
        return getyz;
    }


    /*
     * 查询购物车
     *  http://47.93.215.205:8081/zxWeb/shop/select?userId=3&type=2
     * */
    public static String chaXunShopShow() {
        String getyz = HTTP + "/api/shopCart/GetShopCartlist";
        return getyz;
    }


    /*
     * 修改购物车数量
     *  http://47.93.215.205:8081/zxWeb/shop/update?userId=3&payNum=1&pkId=1
     * */
    public static String changeShopNumShow(int userId, int payNum, int pkId) {
        String getyz = HTTP + "/zxWeb/shop/update?userId=" + userId + "&payNum=" + payNum + "&pkId=" + pkId;
        return getyz;
    }


    /*
     * 删除购物车中指定的商品
     * http://47.93.215.205:8081/zxWeb/shop/delete?shopIds=12,13,14
     * */
    public static String deleteShopShow(int shopIds) {
        String getyz = HTTP + "/zxWeb/shop/delete?shopIds=" + shopIds;
        return getyz;
    }


    /*
     * 购物车结算的接口
     * http://47.93.215.205:8081/zxWeb/personOrder//affirmorder?type=2&ids=14,15&id=3
     * */
    public static String gwcJieSuanShopShow(int type, String ids, int id) {
        String getyz = HTTP + "/zxWeb/personOrder/affirmorder?type=" + type + "&ids=" + ids + "&id=" + id;
        return getyz;
    }

    /*
     * 确认订单
     * http://192.168.1.16:8081/zxWeb/personOrder/affirmorder?id=3&type=2&ids=14,15
     * */
    public static String submitDdShow(int id, int type, String ids) {
        String getyz = HTTP + "/zxWeb/personOrder/affirmorder?id=" + id + "&type=" + type + "&ids=" + ids;
//        String getyz = "http://192.168.1.16:8081/zxWeb/personOrder/affirmorder?id="+id+"&type="+type+"&ids="+ids;
        return getyz;
    }


    /*
     * 购物车提交订单的接口
     * http://192.168.1.16:8081/zxWeb/personOrder/addorder?order=
     * */
    public static String tiJiaoDdShow(SubmitDdTwo order) {
        String getyz = HTTP + "/zxWeb/personOrder/addorder?order=" + order;
//        String getyz = "http://192.168.1.16:8081/zxWeb/personOrder/addorder?order="+order;
        return getyz;
    }


    /**
     * =============2018年7月29日11:01:39徐康康=========================================================
     */

    /*
     * 我的-订单-企业和商家
     * http://47.93.205.215:8081/zxWeb/personOrder/findOrderById?userId=8&status=1
     * status0未支付，1支付
     * */
    public static String dingdanqiyeandshangjia(int userId, String Status) {
        String getyz = HTTP + "/zxWeb/personOrder/findOrderById?userId=" + userId + "&status=" + Status;
        //String getyz = "http://192.168.1.8:8081/zxWeb/personOrder/findOrderById?userId=" + userId + "&status=" + Status;
        return getyz;
    }

    /*
     * 我的-订单-企业和商家-填写快递
     *  http://47.93.215.205:8081/zxWeb/personOrder/insertExpressIntoOrder?pkId=1&express=52555&expressType=中通
     * status0未支付，1支付
     * */
    public static String tianxiekuaidi(int pkId, String express, String expressType) {
        String getyz = HTTP + "/zxWeb/personOrder/insertExpressIntoOrder?pkId=" + pkId + "&express=" + express + "&expressType=" + expressType;

        return getyz;
    }

    //=================================================2018年7月30日22:10:03徐康康====================================================
    /*
     * 注册-填写信息-个人
     *  http://47.93.215.205:8081/zxWeb/user/updateuser?userName=
     *
     * */

    public static String regeste(int type, String phone, String openID, String access_token, String name, String linkman, String address,
                                 String userUrl, String workType, String IDurl, String IdHold, String license, String shopUrl,
                                 String selfSkills, String space, String evaluate, String companyType, String engageIn, String sex,
                                 String workYears, String age,String longitude,String latitude) {
        String getyz = HTTP + "/api/login/register?" +
                "type=" + type +
                "&telenumber=" + phone +
                "&openId=" + openID +
                "&access_token=" + access_token +
                "&realname=" + name +
                "&linkman=" + linkman +
                "&address=" + address +
                "&userUrl=" + userUrl +
                "&workType=" + workType +
                "&IDUrl=" + IDurl +
                "&IDHold=" + IdHold +
                "&license=" + license +
                "&shopUrl=" + shopUrl +
                "&selfSkills=" + selfSkills +
                "&space=" + space +
                "&evaluate=" + evaluate +
                "&companyType=" + companyType +
                "&sex=" + sex +
                "&workYears=" + workYears +
                "&age=" + age +
                "&engageIn=" + engageIn+
                "&longitude=" + longitude+
                "&latitude=" + latitude;
        return getyz;
    }


    public static String getzhuceqiye(String telenumber, String openId, String access_token, String realname, String address, String userUrl,
                                      String IDUrl, String license, String shopUrl, String space,
                                      String evaluate, String companyType, String engageIn) {
        String get = HTTP + "/api/login/register?type=0" + "&telenumber" + telenumber + "&openId" + openId + "&access_token" + access_token + "&realname" + realname
                + "&address" + address + "&userUrl" + userUrl + "&IDUrl" + IDUrl + "&license" + license + "&shopUrl" + shopUrl
                + "&space" + space + "&evaluate" + evaluate + "&companyType" + companyType + "&engageIn" + engageIn;

        return get;
    }

    /*
     * 注册-填写信息-企业
     *  http://47.93.215.205:8081/zxWeb/user/updateuser?userName=
     *
     * */
    public static String zhuceqiye(String userName, String address, String mobile, int permitPhoto, int identifyFacad, int identifyBack, int cardPhoto, int headPhoto, String product, String companySpace, String appraise, String type, int pkId, String idNumber, String companyNumber, String companyName) {
        String getyz = HTTP + "/zxWeb/user/updateuser?userName=" + userName + "&address=" + address + "&mobile=" + mobile + "&permitPhoto=" + permitPhoto + "&identifyFacad=" + identifyFacad + "&identifyBack=" + identifyBack + "&cardPhoto=" + cardPhoto + "&headPhoto=" + headPhoto + "&product=" + product + "&companySpace=" + companySpace + "&appraise=" + appraise + "&type=" + type + "&pkId=" + pkId + "&idNumber=" + idNumber + "&companyNumber=" + companyNumber + "&companyName=" + companyName;
        return getyz;
    }


    /**
     * ====================================微信登陆=======
     */
    /*
     * 微信登陆
     *   http://47.93.215.205:8081/zxWeb/Userthird/saveJskSysUserthird
     * */
    public static String wxdenglu(String openid, String access_token) {
        String getyz = HTTP + "/api/login/GetWechat?openId=" + openid + "&access_token=" + access_token;
        return getyz;
    }


    /*
     * 积分支付即积分兑换
     *   http://47.93.215.205:8081/zxWeb/marketpay/pay?orderId=
     * */
    public static String myJiFenDuiHuan(String orderId) {
        String getyz = HTTP + "/zxWeb/marketpay/pay?orderId=" + orderId;
        return getyz;
    }


    /*
     * 查询转账明细
     *  http://47.93.215.205:8081/zxWeb/transferDetail/selectTransferDetail?userId=2
     * */
    public static String JinEMingXi(int userId) {
        String getyz = HTTP + "/api/Personal/GetAccountDetail?userId=" + userId;
        return getyz;
    }


    /*
     * 金额-提现
     *   http://47.93.215.205:8081/zxWeb/transferDetail/savsebalance?userId=3&balance=100&transferName=haha
     * */
    public static String JinETiXian(int userId, String money) {
        String getyz = HTTP + "/api/AlipayEmbodies/Putforward?userId=" + userId + "&money=" + money;
        return getyz;
    }


    /*
     * 我的地址列表、查询默认地址
     *   http://47.93.215.205:8081/zxWeb/personAddress/findList?userId=3
     * */
    public static String myAddressShow(int userId) {
        String getyz = HTTP + "/api/address/MyAddress?user_id=" + userId;
        return getyz;
    }


    /*
     * 修改地址
     *  http://47.93.215.205:8081/zxWeb//personAddress/createOrUpdatePersonAddress?userName=哈哈&mobile=15677787898&region=哈韩哈&address=健康十分&isDefault=1
     * */
    public static String myXiuGaiAddressShow(String name, String tele, String province, String city, String county, String detaladdress, String status, int user_id) {
        String getyz = HTTP + "/api/address/AddAddress?name=" + name + "&tele=" + tele + "&province=" + province + "&city=" + city + "&county=" + county
                + "&detaladdress=" + detaladdress + "&status=" + status + "&user_id=" + user_id;
//        String getyz = "http://192.168.1.8:8081/zxWeb/personAddress/createOrUpdatePersonAddress?userId="+userId+"&userName="+userName+"&mobile="+mobile+"&region="+region+"&address="+address+"&isDefault="+isisDefault;
        return getyz;
    }

    /*
     * 我的地址列表删除地址
     * //http://47.93.215.205:8081/zxWeb/personAddress/deletePersonAddress?pkId=4
     *
     * */
    public static String deldteaddress(int pkId) {
        String getyz = HTTP + "/zxWeb/personAddress/deletePersonAddress?pkId=" + pkId;
        return getyz;
    }

    /*
     * 订单返回地址
     * //http://47.93.215.205:8081/zxWeb/personAddress/deletePersonAddress?pkId=4
     *
     * */
    public static String setaddress(String orderId, int addressId) {
        String getyz = HTTP + "/zxWeb/personOrder/updaddress?orderId=" + orderId + "&addressId=" + addressId;
        return getyz;
    }

    /*
     * 设置/修改后台密码仅商户企业使用
     * http://47.93.215.205:8081/zxWeb/user/updlogin
     *
     * */
    public static String sethoutai(int pkId, String loginPwd) {
        String getyz = HTTP + "/zxWeb/user/updlogin?pkId=" + pkId + "&loginPwd=" + loginPwd;
        return getyz;
    }


    /*
     * 我的店铺()商铺下面的商家
     * 192.168.1.8:8081/zxWeb/findProduct/findMyProductUserInfo?userId=3
     * */
    public static String mydianpu(int user_id) {
        String getyz = HTTP + "/api/product/MyShop?userId=" + user_id;
        return getyz;
    }

    public static String shop(int user_id) {
        String getyz = HTTP + "/api/product/MyShopBasic?userId=" + user_id;
        return getyz;
    }


    /*
     *个人中心，我的接口
     * http://47.93.215.205:8081/zxWeb/user/updcity?pkId=pkId&city=city;
     *
     * */
    public static String mypersonal(int user_id) {
        String getyz = HTTP + "/api/Personal/MyselfMsg?userId=" + user_id;
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }

    public static String settoaddress(int pkId, String city) {
        String getyz = HTTP + "/zxWeb/user/updcity?pkId=" + pkId + "&city=" + city;
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }

    /***
     * 商机页面=====商机发布接口
     */
    public static String getshangjifabu(String companyname, String username, String require, String info, String valid, String num,
                                        String itemimg, String shopimg, String businessimg, String price, int userId) {
        String getyz = HTTP + "/api/business/addjoinbusiness?companyname=" + companyname + "&username=" + username + "&require=" + require + "&info=" + info
                + "&valid=" + valid + "&num=" + num + "&itemimg=" + itemimg + "&shopimg=" + shopimg + "&businessimg=" + businessimg + "&userId=" + userId;
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }


    /**
     * 首页消息模块
     */
    public static String getshouyexiaoxi() {
        String getyz = HTTP + "/api/apinews/newsCunzai";
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }

    /**
     * 获取消息数量
     */
    public static String getxiaoxishuliang() {
        String getyz = HTTP + "/api/apinews/newsCount";
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }


    /**
     * 获取收货地址
     */
    public static String chakandizhi(int user_id) {
        String getyz = HTTP + "/api/address/MyAddress?user_id=" + user_id;
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }

    /**
     * 删除收货地址
     */
    public static String shanchudizhi(int id, int user_id) {
        String getyz = HTTP + "/api/address/deleteAddress?id=" + id + "&user_id=" + user_id;
        // String getyz = "http://192.168.1.8:8081/zxWeb/user/updcity?pkId="+pkId+"&city="+city;
        return getyz;
    }

    /**
     * 购物车规格
     */
    public static String guige(int productId) {
        String getyz = HTTP + "/api/product/GetSpecByProductId?productId=" + productId;
        return getyz;
    }

    /**
     * 直接购买
     *
     * @return
     */
    public static String zhijiegoumai() {
        String getyz = HTTP + "/api/order/GenerateOrder";
        return getyz;
    }

    /**
     * 支付接口
     *
     * @param paylogId
     * @param paytype
     * @return
     */
    public static String jiesaun(int paylogId, int paytype) {
        String getyz = HTTP + "/api/paymethod/confirmPay?paylogId=" + paylogId + "&paytype=" + paytype;
        return getyz;
    }

    /**
     * 点赞接口
     */
    public static String dianzan() {
        String getyz = HTTP + "/api/commentary/ThumbsUp";
        return getyz;
    }

    /**
     * 根据分类显示轮播
     */
    public static String xianshifenlei(int typeid) {
        String getyz = HTTP + "/api/homepage/GetImgUrlByTypeId?typeId=" + typeid;
        return getyz;

    }

    /**
     * 结算页获取地址
     */
    public static String jiesuandizhi(int user_id, int status) {
        String getyz = HTTP + "/api/address/MyAddress?user_id=" + user_id + "&status=" + status;
        return getyz;
    }

    /**
     * 直接详情进入列表
     */

    public static String zhijiejinruxq(int typeId) {
        String getyz = HTTP + "/api/product/GetProductlistByTypeId?typeId=" + typeId;
        return getyz;
    }

    /**
     * 商品规格
     */

    public static String shangpinguige(int productId) {
        String getyz = HTTP + "/api/product/GetSpecByProductId?productId=" + productId;
        return getyz;
    }

    /*
     *   商品数量
     * */
    public static String shangpinNum() {
        String getyz = HTTP + "/api/shopCart/upShopCartCount";
        return getyz;
    }

    /**
     * 删除商品
     *
     * @return
     */
    public static String deleteshangpin() {
        String getyz = HTTP + "/api/shopCart/deleteShopCart";
        return getyz;
    }

    public static String shoppOrder() {
        String getyz = HTTP + "/api/order/ShopCartOrder";
        return getyz;
    }

    /**
     * 结算
     *
     * @param user_id
     * @param shopID
     * @return
     */
    public static String qujiesuan(int user_id, String shopID) {
        String getyz = HTTP + "/api/product/GetProductMsg?" + "userId=" + user_id + "&shopCardIds=" + shopID;
        return getyz;
    }

    public static String qujiesuan(int user_id, int shopID, int spo, int spt, int num) {
        String getyz = HTTP + "/api/product/GetProductMsg?" + "userId=" + user_id + "&productId=" + shopID + "&specOne=" + spo + "&specTwo=" + spt + "&count=" + num;
        return getyz;
    }

    /**
     * 评论
     *
     * @param type
     * @param id
     * @return
     */
    public static String getPingLun(int type, int id) {
        String s = URLS.HTTP + "/api/server/getCommentList?" + "type=" + type + "&id=" + id;
        return s;
    }

    /**
     * 微信登陆
     *
     * @param openID
     * @param token
     * @return
     */
    public static String WXlogin(String openID, String token) {
        return URLS.HTTP + "/api/login/GetWechat?" + "openId=" + openID + "&access_token=" + token;
    }

    /**
     * 根据大类获取小类
     */
    public static String getdaxiaolei(int typeid) {

        String getyz = URLS.HTTP + "/api/homepage/GetSmallTypeById?typeId=" + typeid+"&userId="+getUser_id();
        return getyz;
    }

    public static String shopFee() {
        return URLS.HTTP + "/api/order/shopFee";
    }

    /*
     *   服务店铺
     * */

    public static String fuwu(int user_id) {
        return URLS.HTTP + "/api/server/getServerShop"+"?userId="+user_id;
    }
    /*
     *  发布服务
     * */

    public static String fabu() {
        return URLS.HTTP + "/api/server/addServer";
    }
    public static String fabu(int user_id,String name,String address,String area,String price ,String requires
            ,String imgUrl,int serverType ,String startTime ,String endTime ,int isNeed,String phone,String lon,String lat){
        return URLS.HTTP + "/api/server/addServer?userId="+user_id+"&name="+name+"&address="+address+"&area="+area+"&price="
                +price+"&requires="+requires+"&imgUrl="+imgUrl+"&serverType="
                +serverType+"&startTime="+startTime+"&endTime="+endTime+"&isNeed="+isNeed+"&phone="+phone
                +"&longitude="+lon+"&latitude="+lat;
    }
// /api/business/addBusiness
    public static String addBusiness(int userId,String goodsname,String goodsinfo,String price,long num,String image,String time,long phone){

        return URLS.HTTP+
                "/api/business/addBusiness?userId="+
                userId+"&goodsname="+goodsname+"&goodsinfo="+
                goodsinfo+"&price="+price+"&num="+num+"&img="+image+"&endtime="+time+"&phone="+phone;
    }

    /**
     * 修改地址
     * @param dynamicLongitude
     * @param dynamicLatitude
     * @return
     */
    public static String updateDynamicAddress(String dynamicLongitude,String dynamicLatitude){

        return URLS.HTTP+
                "/api/Personal/updateAddress?userId="+
                getUser_id()+"&dynamicLongitude="+dynamicLongitude+"&dynamicLatitude="+dynamicLatitude;
    }
    /**
     * 修改地址
     * @param address
     * @param longitude
     * @param latitude
     * @return
     */
    public static String updateAddress(String address,String longitude,String latitude){

        return URLS.HTTP+
                "/api/Personal/updateAddress?userId="+
                getUser_id()+"&address="+address+"&longitude="+
                longitude+"&latitude="+latitude;
    }

    /**
     * 发布产品
     *
     * @return
     */
    public static String postSp() {
        String getyz = HTTP + "/api/product/addProduct";
        return getyz;
    }

}
