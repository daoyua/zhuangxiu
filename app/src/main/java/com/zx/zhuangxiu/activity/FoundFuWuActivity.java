package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.FuWuViewpagerAdapter;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;

import java.util.ArrayList;
import java.util.List;

public class FoundFuWuActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mGoodsTab;
    private ViewPager goodsViewPager;
    private List<String> mList = new ArrayList<String>();
    private FuWuViewpagerAdapter fuWuViewpagerAdapter;
    private ImageView  serves_xiaoxi;
    private TextView  serves_search;
    private TextView serves_back, serves_title;
    private int type = 1;
    private String title = "找服务";
    private List<DaxiaofenleiBean.DataBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_fu_wu);

        Bundle bundle =getIntent().getExtras();
        type = bundle.getInt("type");
        title = bundle.getString("title");


        initView();
        getfenlei();
    }

    private void getfenlei(){

        String url = URLS.getdaxiaolei(6);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DaxiaofenleiBean>() {
            @Override
            public void onSuccess(DaxiaofenleiBean response) {

                if (response.getResult() == 1){
                    mlist.addAll(response.getData());
                    mGoodsTab.setupWithViewPager(goodsViewPager);
                    fuWuViewpagerAdapter = new FuWuViewpagerAdapter(getSupportFragmentManager(), mlist, type);
                    goodsViewPager.setAdapter(fuWuViewpagerAdapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }




    private void initView() {

        serves_back = (TextView) findViewById(R.id.serves_back);
        serves_search = (TextView)findViewById(R.id.serves_search);
        serves_xiaoxi = (ImageView)findViewById(R.id.img_search);
        serves_xiaoxi.setOnClickListener(this);
        serves_search.setOnClickListener(this);
        serves_back.setOnClickListener(this);

        serves_title = (TextView)findViewById(R.id.serves_title);
        serves_title.setText(""+title);

        mGoodsTab = (TabLayout) findViewById(R.id.goods_tab);
        goodsViewPager = (ViewPager) findViewById(R.id.goods_viewpager);
        //tab的标题
        mList.add("装饰装修");
        mList.add("家政保洁");
        mList.add("其他");
        mGoodsTab.setTabMode(TabLayout.MODE_SCROLLABLE);
   /*     fuWuViewpagerAdapter = new FuWuViewpagerAdapter(getSupportFragmentManager(), mList, type);
        goodsViewPager.setAdapter(fuWuViewpagerAdapter);*/
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.serves_back:
                this.finish();
                break;
            case R.id.serves_search:
                intent.setClass(FoundFuWuActivity.this, FaBuFuWuActivity.class);
                startActivity(intent);
                break;
            case R.id.img_search:
                intent.setClass(FoundFuWuActivity.this, ZfwSearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
