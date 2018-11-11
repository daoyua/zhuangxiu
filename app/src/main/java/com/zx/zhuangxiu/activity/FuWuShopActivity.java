package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.FuWuListAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.FuWuDianPuBean;
import com.zx.zhuangxiu.utils.ToastUtil;
import com.zx.zhuangxiu.view.CircleImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;

public class FuWuShopActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private ImageView mBack;
    private CircleImageView mAvatar;
    private TextView mName, mAddress;
    private SmartRefreshLayout mRefresh;
    private ListView mListview;
    List<FuWuDianPuBean.DataBean.ServciesBean> mList = new ArrayList<>();
    private FuWuListAdapter fuWuListAdapter;
    private FuWuDianPuBean.DataBean.ServciesBean dataBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getThumbsup();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setThumbsup(upnum);
                    fuWuListAdapter.update(msg.arg1, mListview, dataBean.getThumbsup());
                    break;
                case 101:
                    upnum--;
                    dataBean.setThumbsup(upnum);
                    fuWuListAdapter.update(msg.arg1, mListview, dataBean.getThumbsup());
                    Toast.makeText(FuWuShopActivity.this, "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private int user;
    private String fuwu;
    private LinearLayout ll_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_wu_shop);
        Intent intent = getIntent();
        user = intent.getIntExtra("UserID", -1);
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mAvatar = findViewById(R.id.avatar);
        mName = findViewById(R.id.name);
        mAddress = findViewById(R.id.address);
        ll_address = findViewById(R.id.ll_address);
        mRefresh = findViewById(R.id.refresh);
        mListview = findViewById(R.id.listview);

        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadData();
            }
        });
        fuWuListAdapter = new FuWuListAdapter(FuWuShopActivity.this, mList, URLS.getUser_id(), this);
        mListview.setAdapter(fuWuListAdapter);

        ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuWuShopActivity.this, MapActivity.class);
                intent.putExtra("map", mAddress.getText().toString());
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {

        if (user != -1) {
            fuwu = URLS.fuwu(user);
        } else {
            fuwu = URLS.fuwu(URLS.getUser_id());
        }

        OkHttpUtils.get(fuwu, new OkHttpUtils.ResultCallback<FuWuDianPuBean>() {
            @Override
            public void onSuccess(FuWuDianPuBean response) {
                mRefresh.finishRefresh();
                if (response.getResult() == 1) {
                    mName.setText(response.getData().getShoper().getNickname());
                    mAddress.setText(response.getData().getShoper().getAddress());
                    String shopUrl = response.getData().getShoper().getShopUrl();
                    if (shopUrl.startsWith("http://") || shopUrl.startsWith("https://")) {
                        Picasso.with(FuWuShopActivity.this)
                                .load(response.getData().getShoper().getShopUrl())
                                .placeholder(R.mipmap.logo_zhanwei)
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mAvatar);
                    } else {
                        Picasso.with(FuWuShopActivity.this)
                                .load(URLS.HTTP + response.getData().getShoper().getShopUrl())
                                .placeholder(R.mipmap.logo_zhanwei)
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mAvatar);
                    }
                    mList.clear();
                    List<FuWuDianPuBean.DataBean.ServciesBean> servcies = response.getData().getServcies();
                    Collections.reverse(servcies);
                    mList.addAll(servcies);
                    fuWuListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Exception e) {
                mRefresh.finishRefresh();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void itemClick(int postion, View view) {
        dataBean = (FuWuDianPuBean.DataBean.ServciesBean) mListview.getItemAtPosition(postion);
        dianZanInfo(postion);
    }

    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 1 + "")
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
                ToastUtil.showShort(FuWuShopActivity.this, "点赞失败！");
            }
        });

    }
}
