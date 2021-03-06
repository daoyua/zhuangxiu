package com.zx.zhuangxiu.model;

public class WeiXinTwo {

    private String appid;
    private String noncestr;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;
    private String packagea =  "Sign=WXPay";

    @Override
    public String toString() {
        return "WeiXinTwo{" +
                "appid='" + appid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", packagea='" + packagea + '\'' +
                '}';
    }

    public String getPackagea() {
        return packagea;
    }

    public void setPackagea(String packagea) {
        this.packagea = packagea;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
