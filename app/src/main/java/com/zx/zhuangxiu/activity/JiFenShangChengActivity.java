package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.JiFenShangChengListAdatper;
import com.zx.zhuangxiu.model.JiFenShangChengBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分商场，个人端才有
 * 修改时间：2018年7月25日17:58。
 * 修改人：
 * 源代码著作人：徐康康
 * */
public class JiFenShangChengActivity extends AppCompatActivity {
    private TextView back;
    private ListView listView;
    private List<JiFenShangChengBean.DataBean>list= new ArrayList<>();
    private JiFenShangChengListAdatper adatper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_fen_shang_cheng);
        back=(TextView) findViewById(R.id.back);
        listView=(ListView)findViewById(R.id.listview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();
    }

    private void init() {
    String url= URLS.jifenshangcheng();
        Log.d("徐康康",url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<JiFenShangChengBean>() {
            @Override
            public void onSuccess(JiFenShangChengBean response) {
                if (response.getResult() == 1 ){
                    list=response.getData();
                    adatper= new JiFenShangChengListAdatper(list,JiFenShangChengActivity.this);
                    listView.setAdapter(adatper);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
