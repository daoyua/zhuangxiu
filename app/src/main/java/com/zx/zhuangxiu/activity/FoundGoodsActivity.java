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
import com.zx.zhuangxiu.adapter.GoodsViewpagerAdapter;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-找产品
 */
public class FoundGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mGoodsTab;
    private ViewPager goodsViewPager;
    private List<String> mList = new ArrayList<String>();
    private GoodsViewpagerAdapter goodsViewpagerAdapter;

    private ImageView goods_search;
    private TextView goods_back;
    private List<DaxiaofenleiBean.DataBean> mlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_goods);
        initView();
        getfenlei();
    }

    private void getfenlei() {

        String url = URLS.getdaxiaolei(5);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DaxiaofenleiBean>() {
            @Override
            public void onSuccess(DaxiaofenleiBean response) {

                if (response.getResult() == 1) {
                    mlist.addAll(response.getData());
                    //tablayout和viewpager关联
                    mGoodsTab.setupWithViewPager(goodsViewPager);
                    goodsViewpagerAdapter = new GoodsViewpagerAdapter(getSupportFragmentManager(), mlist);
                    goodsViewPager.setAdapter(goodsViewpagerAdapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    private void initView() {
        goods_back = (TextView) findViewById(R.id.goods_back);
        goods_search = (ImageView) findViewById(R.id.goods_search);
        goods_back.setOnClickListener(this);
        goods_search.setOnClickListener(this);

        mGoodsTab = (TabLayout) findViewById(R.id.goods_tab);
        goodsViewPager = (ViewPager) findViewById(R.id.goods_viewpager);
        mGoodsTab.setupWithViewPager(goodsViewPager);
       /* //tab的标题
        mList.add("装修建材");
        mList.add("家政服务");
        mList.add("其他");

        //tablayout和viewpager关联
        mGoodsTab.setupWithViewPager(goodsViewPager);
        goodsViewpagerAdapter = new GoodsViewpagerAdapter(getSupportFragmentManager(), mList);
        goodsViewPager.setAdapter(goodsViewpagerAdapter);*/

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.goods_back:
                this.finish();
                break;
            case R.id.goods_search:
                intent.setClass(FoundGoodsActivity.this, SouSuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
