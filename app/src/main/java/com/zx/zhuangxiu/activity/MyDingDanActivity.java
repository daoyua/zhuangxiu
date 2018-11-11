package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.DingDanListViewAdapter;
import com.zx.zhuangxiu.model.MyDingDanBean;
import com.zx.zhuangxiu.model.WorkerListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单，分为三个不同端，企业、商家 、个人，此页面是个人订单列表
 * 修改时间：2018年7月24日10:32。
 * 修改人：徐康康
 * 源代码著作人：徐督佳
 */
public class MyDingDanActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mDingdanListview;
    private RadioGroup mRadioGroup;
    private RadioButton mRb1, mRb2;
    private TextView my_dingdan_back;
    private DingDanListViewAdapter mDingDanListViewAdapter;
    private WorkerListItem workListItems;
    private List<MyDingDanBean.DataBean> workList = new ArrayList<>();//复用我的动态实体类

    private String imageurl = "";
    private int recordId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan);

        initView();
    }

    private void initView() {
        my_dingdan_back=(TextView)findViewById(R.id.my_dingdan_back);
        mRb1 = (RadioButton) findViewById(R.id.my_dingdan_rb1);
        mRb2 = (RadioButton) findViewById(R.id.my_dingdan_rb2);
        mRadioGroup = (RadioGroup) findViewById(R.id.my_dingdan_rg);
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        my_dingdan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mDingdanListview = (ListView) findViewById(R.id.my_dingdan_listview);
        initData("0");


    }

    private void initData( String i) {
        String url = URLS.mydingdangeren(URLS.getUser_id(), i);
        Log.d("徐康康","地址"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyDingDanBean>() {

            @Override
            public void onSuccess(MyDingDanBean response) {

                if (response.getResult() == 1){
                    workList.addAll(response.getData());
                    mDingDanListViewAdapter = new DingDanListViewAdapter(MyDingDanActivity.this, workList);
                    mDingdanListview.setAdapter(mDingDanListViewAdapter);
                }


            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_dingdan_rb1:
                workList.clear();
//                mDingDanListViewAdapter.notify();
                initData("0");//未支付的订单
                break;
            case R.id.my_dingdan_rb2:
                workList.clear();
//                mDingDanListViewAdapter.notify();
                initData("1");//已支付的的订单
                break;

        }
    }

}
