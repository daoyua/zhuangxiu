package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.XiugainichengBean;

public class GaiNichengActivity extends AppCompatActivity {
    private TextView oktext;
    private EditText editname;
    private String headurl;
    private String name;
    private String iphone;
    private TextView gai_back;
    private String editnames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gai_nicheng);
        editname=(EditText)findViewById(R.id.editname);
        oktext=(TextView)findViewById(R.id.oktext);
        gai_back=(TextView)findViewById(R.id.gai_back);

        //返回上一层
        gai_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GaiNichengActivity.this,MyInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        oktext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editnames = editname.getText().toString().trim();

                if (!TextUtils.isEmpty(editnames)){
                    String url = URLS.changenames(URLS.getUser_id(), editnames);
                    OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<XiugainichengBean>() {
                        @Override
                        public void onSuccess(XiugainichengBean response) {
                            if (response.getResult() == 1) {
                                Toast.makeText(GaiNichengActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GaiNichengActivity.this, MyInfoActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(GaiNichengActivity.this,"更改失败",Toast.LENGTH_LONG).show();
                        }
                    });

                }else {
                    Toast.makeText(GaiNichengActivity.this, "请输入要修改的昵称", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

}
