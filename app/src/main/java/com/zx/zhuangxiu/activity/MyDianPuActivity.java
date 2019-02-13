package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.GridViewDianPuAdapter;
import com.zx.zhuangxiu.model.MyDianPu;
import com.zx.zhuangxiu.model.MyShop;
import com.zx.zhuangxiu.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class MyDianPuActivity extends AppCompatActivity {
    private CircleImageView dianpuimage;
    private TextView dianpuname, dizhi, back;
    private GridView gridview;
    private GridViewDianPuAdapter adapter;
    List<MyDianPu.DataBean> list = new ArrayList<>();
    private int intExtra = URLS.getUser_id();
    private SmartRefreshLayout mRefresh;
    private LinearLayout address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dian_pu);

        Intent intent = getIntent();
        int id = intent.getIntExtra("sellerId", -1);
        if (id != -1) {
            intExtra = id;
        }

        gridview = (GridView) findViewById(R.id.gridview);
        dianpuname = (TextView) findViewById(R.id.dianpuname);
        dizhi = (TextView) findViewById(R.id.dizhi);
        back = (TextView) findViewById(R.id.back);
        dianpuimage = (CircleImageView) findViewById(R.id.dianpuimage);
        address = (LinearLayout) findViewById(R.id.address);
        //TODO
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyDianPuActivity.this, MapActivity.class);
                intent.putExtra("map", dizhi.getText().toString());
                if(!TextUtils.isEmpty(longitude)){
                    intent.putExtra("lat",latitude);
                    intent.putExtra("lon",longitude);
                }
                startActivity(intent);
            }
        });


        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                into();
            }
        });

        into();
    }

    private String longitude;
    private String latitude;
    private void into() {
        String shop = URLS.shop(intExtra);
        OkHttpUtils.get(shop, new OkHttpUtils.ResultCallback<MyShop>() {



            @Override
            public void onSuccess(MyShop response) {
                if (response.getResult() == 1) {
                    MyShop.DataBean data = response.getData();
                    if (data != null) {
                        dizhi.setText(response.getData().getAddress());
                        dianpuname.setText(response.getData().getNickname());
                        latitude = data.getLatitude();
                         longitude = data.getLongitude();
                        Picasso.with(getApplicationContext()).load(URLS.HTTP + response.getData().getShopUrl()).error(R.mipmap.logo_zhanwei).placeholder(R.mipmap.logo_zhanwei).fit().into(dianpuimage);
                    }


                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });


        String ur = URLS.mydianpu(intExtra);

        OkHttpUtils.get(ur, new OkHttpUtils.ResultCallback<MyDianPu>() {
            @Override
            public void onSuccess(MyDianPu response) {
                if (response.getResult() == 1) {
                    if (mRefresh != null) {
                        mRefresh.finishRefresh();
                    }
                    list.clear();
                    list.addAll(response.getData());
                    adapter = new GridViewDianPuAdapter(MyDianPuActivity.this, list);
                    gridview.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
