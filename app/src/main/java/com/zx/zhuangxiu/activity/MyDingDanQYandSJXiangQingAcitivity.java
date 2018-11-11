package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.ChengGong;

public class MyDingDanQYandSJXiangQingAcitivity extends AppCompatActivity {
    private TextView textname,textdizhi;
    private EditText editkuaidi,editkuaidihao;
    private Button buttonok;
    private String name,dizhi,phone,pkid;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan_qyand_sjxiang_qing_acitivity);
        Bundle bundle= getIntent().getExtras();
        name=bundle.getString("name");
        dizhi=bundle.getString("dizhi");
        phone=bundle.getString("phone");
        pkid=bundle.getString("pkid");
        textname=(TextView)findViewById(R.id.textname);
        textdizhi=(TextView)findViewById(R.id.textdizhi);
        editkuaidi=(EditText)findViewById(R.id.editkuaidi);
        editkuaidihao=(EditText)findViewById(R.id.editkuaidihao);
        buttonok=(Button)findViewById(R.id.buttonok);
        back=(TextView) findViewById(R.id.back);

        textname.setText("收货人："+name+"     电话:"+phone);
        textdizhi.setText("收货人地址："+dizhi);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }

    private void init() {
        if (editkuaidi.getText().toString()!=null&&!editkuaidi.getText().toString().equals("")&&editkuaidihao.getText().toString()!=null&&!editkuaidihao.getText().toString().equals("")){
            String kd=editkuaidi.getText().toString();
            String dh=editkuaidihao.getText().toString();
            int pk=Integer.parseInt(pkid);
            String url= URLS.tianxiekuaidi(pk,dh,kd);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChengGong>() {
                @Override
                public void onSuccess(ChengGong response) {
                    if (response.getId()==0){
                        Toast.makeText(MyDingDanQYandSJXiangQingAcitivity.this,"处理成功",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(MyDingDanQYandSJXiangQingAcitivity.this,MyDingDanQYandSJActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }
    }
}
