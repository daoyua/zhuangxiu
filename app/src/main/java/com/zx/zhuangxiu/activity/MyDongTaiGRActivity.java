
package com.zx.zhuangxiu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.MyDongTaiGRListAdpter;
import com.zx.zhuangxiu.model.MyDongTaiGR;

import java.util.ArrayList;
import java.util.List;

/*
* 我的动态，个人端，显示的是订单
* 源代码编写人：徐康康，编写时间：2018年7月24日
* 修改人：  修改时间：
*
* */
public class MyDongTaiGRActivity extends AppCompatActivity {
    private ImageView back;
    private ListView listView;
    private List<MyDongTaiGR.DataBean>list= new ArrayList<>();
    private MyDongTaiGRListAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dong_tai_gractivity);
        back=(ImageView) findViewById(R.id.back);
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
        String url= URLS.mydongtaigeren(URLS.getUser_id());

        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyDongTaiGR>() {
            @Override
            public void onSuccess(MyDongTaiGR response) {
                if (response.getResult() == 1){
                    list.addAll(response.getData());
                    adpter= new MyDongTaiGRListAdpter(list,MyDongTaiGRActivity.this);
                    listView.setAdapter(adpter);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
