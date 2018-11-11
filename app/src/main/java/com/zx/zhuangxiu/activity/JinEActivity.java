package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.TiXianBean;

public class JinEActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mMingXi,jine_back, jine_money, jine_tixian;
    private EditText jine_jine, jine_zfbZh;

    private String yue = "";
    private int id;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_e);
        id = getIntent().getIntExtra("id",-1);
        yue = getIntent().getStringExtra("shnegyu");
        initView();
    }

    private void initView() {
        mMingXi = findViewById(R.id.jine_mingxi);
        jine_back =  findViewById(R.id.jine_back);
        jine_money = findViewById(R.id.jine_money);
        jine_tixian =  findViewById(R.id.jine_tixian);
        jine_jine = findViewById(R.id.jine_jine);
        jine_zfbZh = findViewById(R.id.jine_zfbZh);
        warning = findViewById(R.id.warning);

        jine_tixian.setOnClickListener(this);
        mMingXi.setOnClickListener(this);
        jine_back.setOnClickListener(this);




        jine_money.setText(""+yue+"元");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
//                break;
                setResult(RESULT_OK);
                finish();
                return false;//拦截事件
        }

        return super.onKeyDown(keyCode, event);

    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.jine_mingxi:
                intent.setClass(this, MingXiActivity.class);
                startActivity(intent);
                break;
            case R.id.jine_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.jine_tixian:
                String jine = jine_jine.getText().toString().trim();
                if (TextUtils.isEmpty(jine)){
                    Toast.makeText(this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(jine)>Double.parseDouble(yue)){
                    warning.setVisibility(View.VISIBLE);
                    return;
                }else {
                    warning.setVisibility(View.GONE);
                }
                if (!TextUtils.isEmpty(jine)&&Integer.parseInt(jine)>=100){
                    getTiXianInfo(URLS.getUser_id(),jine);
                }else {
                    Toast.makeText(this, "请输入正确的金额", Toast.LENGTH_SHORT).show();
                }


                break;
            default:
                break;
        }

    }

    private void getTiXianInfo(int id, String s2) {
        String url = URLS.JinETiXian(id, s2);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<TiXianBean>() {
            @Override
            public void onSuccess(TiXianBean response) {
                if(response.getResult() == 1){
                    jine_jine.setText("");
                    Toast.makeText(JinEActivity.this, "恭喜您，申请提现成功，请等待管理员审核!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(JinEActivity.this, "很抱歉，"+response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
