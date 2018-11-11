package com.zx.zhuangxiu.wxapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.BindPhoneActivity;
import com.zx.zhuangxiu.activity.HomeActivity;
import com.zx.zhuangxiu.model.WXdenglu;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信登陆
 * 徐康
 * 2018年5月30日
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    String access = null;
    String openId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
        api.handleIntent(getIntent(), this);//执行微信回调的必须方法，发起页面

    }


    @Override
    public void onReq(BaseReq baseReq) {

    }
    @Override
    public void onResp(BaseResp baseResp) {
        Log.e("TAG", baseResp.errCode + "===" + baseResp.errStr);
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (baseResp.getType() == 2) {
                    finish();
                    return;
                }
                String code = ((SendAuth.Resp) baseResp).code;
                getAccessToken(code);//获取Code值，用于获取用户信息
//                Login(code);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(WXEntryActivity.this, "微信登陆失败，请尝试其他登录方式", Toast.LENGTH_LONG).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(WXEntryActivity.this, "您的登陆请求被微信拒绝，请尝试其他登录方式", Toast.LENGTH_LONG).show();
                break;

        }

    }

    /**
     * 微信登录
     * 获取用户信息
     * 在次方法中做了数据上传，数据对比以及跳转页面逻辑。
     */
    private void getAccessToken(final String code) {
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append(Constants.APP_ID)
                .append("&secret=")
                .append(Constants.SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");

                if (!TextUtils.isEmpty(access)&&!TextUtils.isEmpty(openId)) {
                    //提交到服务器数据库，并跳转
                    String url = URLS.wxdenglu(openId, access);

                    OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<WXdenglu>() {
                        @Override
                        public void onSuccess(WXdenglu response) {

                            switch (response.getData().getState()) {
                                case 0://跳转注册
                                    Intent intent = new Intent(WXEntryActivity.this, BindPhoneActivity.class);
                                    intent.putExtra("oppid",openId);
                                    intent.putExtra("token",access);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case 1://直接登陆
                                    int userId = response.getData().getUserId();
                                    //获取sharedPreferences对象
                                    SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                                    //获取editor对象
                                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                                    editor.putInt("userId", userId);
                                    //提交
                                    editor.commit();//提交修改
                                    URLS.setUser_id(userId);
                                    Intent intent1 = new Intent(WXEntryActivity.this, HomeActivity.class);
                                    startActivity(intent1);
                                    finish();
                                    break;

                            }
                        }

                        @Override
                        public void onFailure(Exception e) {

                        }
                    });


                } else {
                    Toast.makeText(WXEntryActivity.this, "登录失败,请尝试其他登陆" + code, Toast.LENGTH_SHORT).show();
                    Log.d("徐康", "第二个值" + code);
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(WXEntryActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        OkHttpUtils.get(loginUrl.toString(), resultCallback);
    }

}
