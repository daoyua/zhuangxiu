package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.YuShiListViewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.LunbofenleiBean;
import com.zx.zhuangxiu.model.SyProductOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class HomeYuShiActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private ListView mYuShiListView;
    private TextView yushi_back, mYuShiTitle;

    private YuShiListViewAdapter mYuShiListViewAdapter;
    private ImageView yushi_img, yushi_search;

    private List<SyProductOne.DataBean> syProductTwoList = new ArrayList<>();

    private String title;
    private int weizhi;
    private int lunboid;

    private List<LunbofenleiBean.DataBean> mlist = new ArrayList<>();
    private Banner lunbobanner;

    private SyProductOne.DataBean dataBean;
    private int userID;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getUpnum();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setUpnum(upnum);
                    mYuShiListViewAdapter.update(msg.arg1, mYuShiListView, dataBean.getUpnum());
                    break;
                case 101:
                    upnum--;
                    dataBean.setUpnum(upnum);
                    mYuShiListViewAdapter.update(msg.arg1, mYuShiListView, dataBean.getUpnum());
                    Toast.makeText(getApplicationContext(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SmartRefreshLayout mRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_yu_shi);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("title");
        weizhi = bundle.getInt("weizhi");
        lunboid = bundle.getInt("lunboid");

        userID = URLS.getUser_id();


        initView();
        getlubotu();
    }

    private void initView() {
        mYuShiTitle = (TextView) findViewById(R.id.yushi_title);
        mYuShiTitle.setText("" + title);

        yushi_back = (TextView) findViewById(R.id.yushi_back);
        yushi_img = (ImageView) findViewById(R.id.yushi_img);
        lunbobanner = (Banner) findViewById(R.id.jinruxq_bannner);


        if (weizhi == 1) {
            yushi_img.setBackgroundResource(R.drawable.yushi1);
        } else if (weizhi == 2) {
            yushi_img.setBackgroundResource(R.drawable.diping1);
        } else if (weizhi == 3) {
            yushi_img.setBackgroundResource(R.drawable.jiudian1);
        } else {
            yushi_img.setBackgroundResource(R.drawable.wujin1);
        }

        yushi_back.setOnClickListener(this);

        mYuShiListView = (ListView) findViewById(R.id.yushi_listview);

        yushi_search = (ImageView) findViewById(R.id.yushi_search);
        yushi_search.setOnClickListener(this);
        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getInfo();
            }
        });
        getInfo();
    }


    /**
     * 获取轮播图
     */
    private void getlubotu() {

        String url1 = URLS.xianshifenlei(lunboid);
        Log.e("lunboid:",lunboid+"");
        OkHttpUtils.get(url1, new OkHttpUtils.ResultCallback<LunbofenleiBean>() {
            @Override
            public void onSuccess(LunbofenleiBean response) {

                if (response.getResult() == 1) {
                    mlist.add(response.getData());
                    for (int i = 0; i < mlist.size(); i++) {
                        List<String> imageurl = new ArrayList<>();
                        LunbofenleiBean.DataBean dataBean = mlist.get(i);
                        if (dataBean == null) {
                            ToastUtil.show(HomeYuShiActivity.this, "没有数据", Toast.LENGTH_LONG);
                            return;
                        }
                        String im = dataBean.getDetailUrl();
                        if (im != null && im.length() > 0 && im.contains(",")) {
                            String[] imagh = im.split(",");
                            for (String img : imagh) {
                                imageurl.add(URLS.HTTP + img);
                            }
                        }
                        //设置内置样式，共有六种可以点入方法内逐一体验使用。
                        lunbobanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        //设置图片加载器，图片加载器在下方
                        lunbobanner.setImageLoader(new MyLoaders());
                        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                        lunbobanner.setBannerAnimation(Transformer.Default);
                        //设置轮播间隔时间
                        lunbobanner.setDelayTime(3000);
                        //设置是否为自动轮播，默认是“是”。
                        lunbobanner.isAutoPlay(true);
                        //设置指示器的位置，小点点，左中右。
                        lunbobanner.setIndicatorGravity(BannerConfig.CENTER);
                                /*//以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                                .setOnBannerListener(this)
                                //必须最后调用的方法，启动轮播图。
                                .start();*/
                        lunbobanner.setImages(imageurl);
                        lunbobanner.start();
                    }
                }

            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "轮播请求失败");
            }
        });
    }

    @Override
    public void itemClick(int postion, View view) {

        dataBean = (SyProductOne.DataBean) mYuShiListView.getItemAtPosition(postion);
        dianZanInfo(postion);

    }

    /**
     * 点赞接口
     */
    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 0 + "")
                .build();
        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean response) {
                Message message = Message.obtain();
                if (response.getData().getState() == 1) {
                    message.what = 100;
                    message.arg1 = postion;
                    handler.sendMessage(message);
                } else {
                    message.what = 101;
                    message.arg1 = postion;
                    handler.sendMessage(message);
                }

            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "点赞失败！");
            }
        });

    }


    /**
     * 自定义图片加载器
     */
    public class MyLoaders extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    /**
     * 获得商品列表
     */
    private void getInfo() {
        String url = URLS.zhijiejinruxq(weizhi);
//        Log.d("xxx", "直接进入-----url----"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyProductOne>() {
            @Override
            public void onSuccess(SyProductOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    syProductTwoList.removeAll(syProductTwoList);
                    syProductTwoList.addAll(response.getData());
//                    Log.d("xxx", "直接进入-----syProductTwoList----"+syProductTwoList);
                    mYuShiListViewAdapter = new YuShiListViewAdapter(HomeYuShiActivity.this, syProductTwoList, HomeYuShiActivity.this);
                    mYuShiListView.setAdapter(mYuShiListViewAdapter);
                } else {
                    Toast.makeText(HomeYuShiActivity.this, "非常抱歉，暂时没有数据！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.yushi_back:
                this.finish();
                break;
            case R.id.yushi_search:
                intent.setClass(HomeYuShiActivity.this, SouSuoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
