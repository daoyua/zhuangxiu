package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.BindingzfbBean;

public class BindingZhifubaoActivity extends AppCompatActivity {

    private ImageView backimage;
    private EditText kaihumingeditext;
    private EditText zhanghaoeditext;
    private EditText aginzhanghao;
    private TextView tijiaotedxt;
    private String kaihuname;
    private String zhanghao;
    private String aginzhanghaos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_zhifubao);

        //找控件
        initview();
        //提交请求
        gettijiao();
        //返回上一层
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 找控件
     */
    private void initview() {
        backimage = findViewById(R.id.zhifubao_fanhui);
        kaihumingeditext = findViewById(R.id.zhifubao_kaihuming);
        zhanghaoeditext = findViewById(R.id.zhifubao_zhanghao);
        aginzhanghao = findViewById(R.id.zhifubao_aginzhanghao);
        tijiaotedxt = findViewById(R.id.zhifubao_tijiao);
    }


    /**
     * 绑定支付宝请求
     */
    public void gettijiao(){

        tijiaotedxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kaihuname = kaihumingeditext.getText().toString().trim();
                zhanghao = zhanghaoeditext.getText().toString().trim();
                aginzhanghaos = aginzhanghao.getText().toString().trim();
                if (!kaihuname.equals("")&&!zhanghao.equals("")&&!aginzhanghao.equals("")){
                    if (zhanghao.equals(aginzhanghaos)){
                        String url = URLS.tijiaozhifubao(URLS.getUser_id(),zhanghao,aginzhanghaos);
                        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BindingzfbBean>() {
                            @Override
                            public void onSuccess(BindingzfbBean response) {

                                if (response.getResult() == 1){
                                    Toast.makeText(getApplicationContext(),"绑定成功",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(getApplicationContext(),"绑定失败",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {
                        Toast.makeText(getApplicationContext(),"请确认两次输入的账号是一致的！！！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请确认信息都已经填写！！！",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
