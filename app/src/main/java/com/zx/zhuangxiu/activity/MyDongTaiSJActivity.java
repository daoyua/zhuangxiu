package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.MyDongTaiSJListAdapter;
import com.zx.zhuangxiu.model.MyDongTaiSJ;
import com.zx.zhuangxiu.model.MyDongTaiSJOne;

import java.util.ArrayList;
import java.util.List;

/*
 * 我的动态，商家端，显示的是发布产品
 * 源代码编写人：徐康康，编写时间：2018年7月25日
 * 修改人：  修改时间：
 *未写adpter，URLS，xml
 * */
public class MyDongTaiSJActivity extends AppCompatActivity {
    private TextView back;
    private ListView listView;
    private List<MyDongTaiSJOne>list= new ArrayList<>();
    private MyDongTaiSJListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dong_tai_sj);
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
        int id = URLS.getUser_id();
        String url= URLS.mydongshangjia(id);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyDongTaiSJ>() {
            @Override
            public void onSuccess(MyDongTaiSJ response) {
                if (response.getId()==0){
                    list=response.getData();
                    adapter=new MyDongTaiSJListAdapter(list,MyDongTaiSJActivity.this);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
