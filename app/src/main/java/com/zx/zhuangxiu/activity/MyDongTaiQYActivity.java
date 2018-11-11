package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.MyDongTaiQYListAdapter;
import com.zx.zhuangxiu.model.MyqiyedongtaiBean;

import java.util.ArrayList;
import java.util.List;

/*
 * 我的动态，企业端，显示的是发布的项目
 * 源代码编写人：徐康康，编写时间：2018年7月25日
 * 修改人：  修改时间：
 *
 * */
public class MyDongTaiQYActivity extends AppCompatActivity {
    private ImageView back;
    private ListView listView;
    private List<MyqiyedongtaiBean.DataBean>list=new ArrayList<>();
    private MyDongTaiQYListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dong_tai_qy);
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
        String url= URLS.mydongqiye(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyqiyedongtaiBean>() {
            @Override
            public void onSuccess(MyqiyedongtaiBean response) {
                if (response.getResult() == 1){
                    list.addAll(response.getData());
                    adapter= new MyDongTaiQYListAdapter(list,MyDongTaiQYActivity.this);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
