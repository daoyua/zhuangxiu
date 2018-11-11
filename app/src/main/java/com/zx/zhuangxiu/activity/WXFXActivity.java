package com.zx.zhuangxiu.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.R;

import java.io.ByteArrayOutputStream;

public class WXFXActivity extends AppCompatActivity {
    private String url = "http://sj.qq.com/myapp/detail.htm?apkName=com.zx.zhuangxiu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxfx);
        final IWXAPI mwxapi = WXAPIFactory.createWXAPI(WXFXActivity.this, Constants.APP_ID);
        mwxapi.registerApp(Constants.APP_ID);
        wxfx(mwxapi);
    }

    private void wxfx(IWXAPI mwxapi) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "大时圈";
        msg.description = "会装修的全在这里";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.logofenxiang);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        thumb.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        msg.thumbData = baos.toByteArray();
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.scene = SendMessageToWX.Req.WXSceneSession;//聊天界面
        req.message = msg;
        req.transaction = String.valueOf(System.currentTimeMillis());
        mwxapi.sendReq(req);
        finish();
    }
}
