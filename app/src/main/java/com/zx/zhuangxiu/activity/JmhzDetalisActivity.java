package com.zx.zhuangxiu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.R;

public class JmhzDetalisActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView jmhz_details_hezuo,fuwu_details_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmhz_detalis);

        initView();
    }

    private void initView() {
        jmhz_details_hezuo = (TextView)findViewById(R.id.jmhz_details_hezuo);
        fuwu_details_back = (TextView)findViewById(R.id.fuwu_details_back);
        fuwu_details_back.setOnClickListener(this);
        jmhz_details_hezuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jmhz_details_hezuo:

                Toast.makeText(JmhzDetalisActivity.this, "点击加盟合作", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fuwu_details_back:

                Toast.makeText(JmhzDetalisActivity.this, "点击加盟合作", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }
}
