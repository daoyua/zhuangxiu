package com.zx.zhuangxiu.activity;

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
import com.zx.zhuangxiu.model.BaseBean;

public class AddAccountAcitivity extends AppCompatActivity {

    private EditText viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_acitivity);
        viewById = findViewById(R.id.editname);
        TextView oktext = findViewById(R.id.oktext);
        oktext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upData();
            }
        });
        TextView mBack = findViewById(R.id.tv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }

    private void upData() {
      String s = viewById.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "请先设置密码", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.get(URLS.HTTP + "/api/Personal/Setup?userId=" + URLS.getUser_id() + "&userPwd=" + s + "&confirmPwd=" + s, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(AddAccountAcitivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
