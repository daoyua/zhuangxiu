package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.JmhzListViewAdapter;
import com.zx.zhuangxiu.model.BusinessJmhzBean;

import java.util.List;

public class JiamemghezuoActivity extends AppCompatActivity {

    private TextView backtextview;
    private ListView jiamenghzlistview;
    private TextView hezuotextview;
    private JmhzListViewAdapter mJmhzListViewAdapter;
    private List<BusinessJmhzBean.DataBean> jmhzTwoList;
    private SmartRefreshLayout mRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiamemghezuo);

        initview();
        getJmhzInfo();

        /**
         * 返回
         */
        backtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 跳转到发布加盟合作
         */
        hezuotextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JiamemghezuoActivity.this, FabushangjiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initview() {

        backtextview = findViewById(R.id.jiamenghz_back);
        jiamenghzlistview = findViewById(R.id.jiamenghz_listview);
        hezuotextview = findViewById(R.id.jiamenghz_hezuo);

        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getJmhzInfo();
            }
        });
    }

    private void getJmhzInfo() {
        String url = URLS.sjJmhzShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BusinessJmhzBean>() {
            @Override
            public void onSuccess(BusinessJmhzBean response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    jmhzTwoList.clear();
                    jmhzTwoList = response.getData();
                    mJmhzListViewAdapter = new JmhzListViewAdapter(JiamemghezuoActivity.this, jmhzTwoList);
                    jiamenghzlistview.setAdapter(mJmhzListViewAdapter);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
            }
        });
    }

}



