package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.PhotoyzBean;
import com.zx.zhuangxiu.model.XiugainichengBean;

public class BindingPhoneActivity extends AppCompatActivity {
    private EditText editphone,editcode;
    private TextView textgetcode,buttonok;
    private String headurl;
    private String name;
    private String iphones;
    private String code;
    private TextView toutiao_back;
    private String shoujiaho;
    private String inputcode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_phone);

        toutiao_back = (TextView) findViewById(R.id.toutiao_back);
        editphone = (EditText) findViewById(R.id.editphone);
        editcode = (EditText) findViewById(R.id.editcode);
        textgetcode = (TextView) findViewById(R.id.textgetcode);
        buttonok = (TextView) findViewById(R.id.bindingbuttonok);


        toutiao_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 获取验证码
         */
        textgetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoujiaho = editphone.getText().toString().trim();
                if (!shoujiaho.toString().equals("")) {
                    String url = URLS.getphonecode(shoujiaho);
                    OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<PhotoyzBean>() {
                        @Override
                        public void onSuccess(PhotoyzBean response) {
                            if (response.getResult() == 1) {
//                                code=response.getData().getClass();
                                Toast.makeText(BindingPhoneActivity.this, "验证码发送成功", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(BindingPhoneActivity.this, "验证码发送失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(BindingPhoneActivity.this, "手机号是空", Toast.LENGTH_LONG).show();
                }
            }

        });

        /**
         * 点击确认按钮修改手机号
         * Toast.makeText(BindingPhoneActivity.this, shoujiaho, Toast.LENGTH_LONG).show();
         */
        buttonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoujiaho = editphone.getText().toString().trim();
                inputcode1 = editcode.getText().toString().trim();
                if (!shoujiaho.equals("")&&!inputcode1.equals("")){
                    String url= URLS.changephone(URLS.getUser_id(),shoujiaho, inputcode1);
                    OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<XiugainichengBean>() {
                        @Override
                        public void onSuccess(XiugainichengBean response) {
                            if (response.getResult() == 1){
                                Toast.makeText(BindingPhoneActivity.this,"更改成功",Toast.LENGTH_LONG).show();
                                Intent intent =new Intent(BindingPhoneActivity.this,MyInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Exception e) {

                        }
                    });

                }else {
                    Toast.makeText(BindingPhoneActivity.this,"输入内容不能为空",Toast.LENGTH_LONG).show();
                }

            }
        });






    }
}

//                Log.d("徐康康","点击了");
//        inputcode = editcode.getText().toString().trim();
//        if (!shoujiaho.equals("")&&!inputcode.equals("")){
//            if (!inputcode.equals("")){
//
//                Log.d("徐康康","地址"+url1);
//                OkHttpUtils.get(url1, new OkHttpUtils.ResultCallback<XiugainichengBean>() {
//                    @Override
//                    public void onSuccess(XiugainichengBean response) {
//                        if (response.getResult() == 1){
//                           Toas t.makeText(BindingPhoneActivity.this,"更改成功",Toast.LENGTH_LONG).show();
//                            Intent intent =new Intent(BindingPhoneActivity.this,MyInfoActivity.class);
////                                    Bundle bundle=new Bundle();
////                                    bundle.putString("headurl",headurl);
////                                    bundle.putString("name",name);
////                                    bundle.putString("iphone",iphone);
////                                    intent.putExtras(bundle);
//                            startActivity(intent);
//                            finish();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//
//                    }
//                });
//            }else {
//                Toast.makeText(BindingPhoneActivity.this,"验证码不正确",Toast.LENGTH_LONG).show();
//            }
//        }else {
//            Toast.makeText(BindingPhoneActivity.this,"输入内容不能为空",Toast.LENGTH_LONG).show();
//        }
//    }
//});
