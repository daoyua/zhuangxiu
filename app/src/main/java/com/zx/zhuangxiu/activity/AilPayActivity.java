package com.zx.zhuangxiu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zx.zhuangxiu.ali.AuthResult;
import com.zx.zhuangxiu.ali.PayResult;

import java.util.Map;

/**
 * 支付宝支付调起界面，跳转需要传入 参数。
 * */
public class AilPayActivity extends AppCompatActivity {
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ail_pay);
        final String orderInfo =   getIntent().getStringExtra("pay");

//         = bundle.getString("alipay");//"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018051260120382&biz_content=%7B%22body%22%3A%2220180718175826228%22%2C%22out_trade_no%22%3A%2220180718175826228%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%2220180718175826228%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdomo.qhddqsp.com%3A9092%2Fpay%2Fnotify%2FalipayAppPay&sign=NAnmua%2FHagADuQdx3G8EtSm3yqGEOXNn2Q1a49V57JOxV0HKTYjfQFoTmzWB%2BHo6UsjDiaGPNIcyIDQ7pjdMZyTWqaJC6Uu%2BOW5EU%2BMaOQ%2BWqQzi%2FipF%2Bmobf8nhhVIOj3r%2F8zcAc3VHuKt%2BadHHDbGNT2Qi0iOZ1yUorL1yrTJFJ625Ylf5O3h1f5sjTgb%2FMh1CCxdb3L5MzS7UWsfkMYHZaJYr8pD6RIXrKMleCRDEVYoS7H%2BLygDLyndXULjVkGVFO2eIRMoJAfCN2v1FNJEY%2BIfOPrsB6kh5VABcFbh%2FHWzWUuhBL1DGQjmMAwoJcMGUP9n3EX51r7Qvjff%2FIA%3D%3D&sign_type=RSA2&timestamp=2018-07-18+18%3A04%3A02&version=1.0";   // 订单信息




        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AilPayActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
        finish();
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Log.e("AilPayActivity", resultInfo + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(AilPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AilPayActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(AilPayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(AilPayActivity.this,HomeActivity.class);
//                        startActivity(intent);
                        finish();

                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(AilPayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AilPayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
