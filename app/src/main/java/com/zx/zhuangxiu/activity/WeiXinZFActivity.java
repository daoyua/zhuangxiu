package com.zx.zhuangxiu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.R;

import org.json.JSONException;
import org.json.JSONObject;

//微信支付界面，需要传入参数
public class WeiXinZFActivity extends AppCompatActivity {
    String appId, partnerId, prepayId, nonceStr, timeStamp, packageValue, sign;
    private String payData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_zf);
        payData = getIntent().getStringExtra("pay");
        payData.substring(0,payData.length()-1);
        try {
            JSONObject jsonObject=new JSONObject(payData);
            appId		= jsonObject.getString("appid");
            partnerId		= jsonObject.getString("partnerid");
            prepayId		= jsonObject.getString("prepayid");
            nonceStr		= jsonObject.getString("noncestr");
            timeStamp	= jsonObject.getString("timestamp");
            packageValue	= jsonObject.getString("package");
            sign			= jsonObject.getString("sign");
        } catch (JSONException e) {
            e.printStackTrace();
        }

     /*   appId = bundle.getString("appId");
        partnerId = bundle.getString("partnerId");
        prepayId = bundle.getString("prepayId");
        nonceStr = bundle.getString("nonceStr");
        timeStamp = bundle.getString("timeStamp");
        packageValue = bundle.getString("packageValue");
        sign = bundle.getString("sign");*/



        final IWXAPI iwxapi = WXAPIFactory.createWXAPI(WeiXinZFActivity.this, null);
        iwxapi.registerApp(Constants.APP_ID);
        PayReq req = new PayReq();
        req.appId = appId;
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.nonceStr = nonceStr;
        req.timeStamp = timeStamp;
        req.packageValue = packageValue;
        req.sign = sign;
        iwxapi.sendReq(req);
    finish();
    }
}
