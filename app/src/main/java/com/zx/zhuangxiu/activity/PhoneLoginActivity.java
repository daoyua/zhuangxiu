package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.FisrstLoginBean;
import com.zx.zhuangxiu.model.YanzhengmaBean;

import java.util.ArrayList;
import java.util.List;

public class PhoneLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView phone_login, phone_gerenzhuce, phone_qiyezhuce, phone_login_yzm;
    private ImageView phone_login_back, wechart;
    private EditText phone_login_phonenum, phone_login_yanzm;

    private IWXAPI iwxapi;

    private List<FisrstLoginBean> mlist = new ArrayList<>();
    private CountDownTimer timer;
    private TextView phone_login_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
        int anInt = sharedPreferences.getInt("userId", -1);
        if (anInt != -1) {
            URLS.setUser_id(anInt);
            Intent intent1 = new Intent(PhoneLoginActivity.this, HomeActivity.class);
            startActivity(intent1);
            finish();
        } else {
            initView();
        }

    }

    private long mExitToastTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //点击两次退出
    public void exit() {
        if ((System.currentTimeMillis() - mExitToastTime) > 2000) {
            Toast.makeText(PhoneLoginActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitToastTime = System.currentTimeMillis();
        } else {
            Intent MyIntent = new Intent(Intent.ACTION_MAIN);
            MyIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(MyIntent);
            finish();
            System.exit(0);
        }

    }

    private void initView() {
        phone_login_phonenum = (EditText) findViewById(R.id.phone_login_phonenum);  //输入手机号
        phone_login_yanzm = (EditText) findViewById(R.id.phone_login_yanzm);  //输入的验证码

        phone_login_yzm = (TextView) findViewById(R.id.phone_login_yzm);  //获取验证码
        //获取验证码
        phone_login_register = (TextView) findViewById(R.id.phone_login_register);
        //TODO
//        phone_login_register.setVisibility(View.VISIBLE);
        phone_login = (TextView) findViewById(R.id.phone_login);
        phone_login_back = (ImageView) findViewById(R.id.phone_login_back);
//        phone_gerenzhuce = (TextView) findViewById(R.id.phone_gerenzhuce);
//        phone_qiyezhuce = (TextView) findViewById(R.id.phone_qiyezhuce);
        wechart = (ImageView) findViewById(R.id.wechart);

        phone_login.setOnClickListener(this);
        phone_login_back.setOnClickListener(this);
     /*   phone_gerenzhuce.setOnClickListener(this);
        phone_qiyezhuce.setOnClickListener(this);*/
        phone_login_register.setOnClickListener(this);
        phone_login_yzm.setOnClickListener(this);
        wechart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.phone_login:
                getlogin();
                break;
            //TODO  注册
            case R.id.phone_login_register:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.phone_login_yzm:
                getyanzhengma();
                break;
            case R.id.phone_login_back:
                this.finish();
                break;
            case R.id.wechart:
                iwxapi = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);//此处的s:APP_ID
                iwxapi.registerApp(Constants.APP_ID);
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "uiuioujio";
                iwxapi.sendReq(req);//调起微信，并跳转只WXEntryActivity
                break;

        }
    }

    private void CountDown(final TextView textView) {
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        textView.setEnabled(false);
        timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                textView.setText((millisUntilFinished / 1000) + "秒后可重发");
                textView.setTextColor(getResources().getColor(R.color.gray));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText("获取验证码");
                textView.setTextColor(getResources().getColor(R.color.green));
            }
        };
        timer.start();
    }


    /**
     * 获取验证码
     */
    public void getyanzhengma() {
        String teleNum = phone_login_phonenum.getText().toString().trim();
        if (!TextUtils.isEmpty(teleNum) && RegexUtils.isMobileExact(teleNum)) {
            CountDown(phone_login_yzm);
            String url = URLS.getphonecode(teleNum);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<YanzhengmaBean>() {
                @Override
                public void onSuccess(YanzhengmaBean response) {
                    if (response.getResult() == 1) {

                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });

        } else {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 登陆接口
     */
    public void getlogin() {
        final String mtelephone = phone_login_phonenum.getText().toString().trim();
        String yanzhengma = phone_login_yanzm.getText().toString().trim();
        if (TextUtils.isEmpty(mtelephone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!RegexUtils.isMobileExact(mtelephone)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(yanzhengma)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = URLS.Firstlogin(mtelephone, yanzhengma);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<FisrstLoginBean>() {
            @Override
            public void onSuccess(FisrstLoginBean response) {
                if (response.getResult() == 1) {
                    if (response.getData().getState() == 1) {
                        Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                        final int userId = response.getData().getUserId();
                        //获取sharedPreferences对象
                        SharedPreferences sharedPreferences = getSharedPreferences("zx", Context.MODE_PRIVATE);
                        //获取editor对象
                        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                        editor.putInt("userId", userId);
                        //提交
                        editor.commit();//提交修改
                        URLS.setUser_id(userId);
                        Intent intent = new Intent(PhoneLoginActivity.this, HomeActivity.class);
                        intent.putExtra("UserID", userId);
                        startActivity(intent);
                        PhoneLoginActivity.this.finish();

                    } else {
                        Intent intent = new Intent(PhoneLoginActivity.this, LoginActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("shooujihao", mtelephone);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), "登陆失败，请查看您的手机号和验证码是否正确", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
