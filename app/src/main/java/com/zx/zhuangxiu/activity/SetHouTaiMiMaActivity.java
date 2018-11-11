package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.ChengGong;

public class SetHouTaiMiMaActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView zhanghao;
    private EditText ehoutaimima;
    private TextView oktext, company_regist_back;
    private String mun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_hou_tai_mi_ma);
        Bundle bundle =getIntent().getExtras();
        mun=bundle.getString("账号");
        init();
    }

    private void init() {
        zhanghao = (TextView) findViewById(R.id.zhanghao);
        ehoutaimima = (EditText) findViewById(R.id.ehoutaimima);
        oktext = (TextView) findViewById(R.id.oktext);
        company_regist_back = (TextView) findViewById(R.id.company_regist_back);

        oktext.setOnClickListener(this);
        zhanghao.setText(mun);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.oktext:
                if (ehoutaimima.getText().toString()!=null&&!ehoutaimima.getText().equals("")){
                    String url= URLS.sethoutai(URLS.getUser_id(),ehoutaimima.getText().toString());
                    OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChengGong>() {
                        @Override
                        public void onSuccess(ChengGong response) {
                            if (response.getId()==0){
                                Toast.makeText(SetHouTaiMiMaActivity.this,"设置成功",Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {

                        }
                    });
                }else {
                    Toast.makeText(SetHouTaiMiMaActivity.this,"设置的密码不能为空",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.company_regist_back:
                finish();
                break;
            default:
                break;
        }
    }
}
