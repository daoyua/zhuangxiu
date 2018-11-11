package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.MingXiAdapter;
import com.zx.zhuangxiu.model.MingXiBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MingXiActivity extends AppCompatActivity {

    private ListView mMingxiListview;
    private TextView mingxi_back;
    private MingXiAdapter mingXiAdapter;

    private List<MingXiBean.DataBean> jinEMxTwoList = new ArrayList<>();
private SmartRefreshLayout mRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ming_xi);

        initView();
        getMingXiInfo();
    }

    private void initView() {
        mMingxiListview = (ListView) findViewById(R.id.mingxi_listview);
        mingxi_back=(TextView)findViewById(R.id.mingxi_back);
        mingxi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getMingXiInfo();
            }
        });
    }

    private void getMingXiInfo() {
            String url = URLS.JinEMingXi(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MingXiBean>() {
            @Override
            public void onSuccess(MingXiBean response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if(response.getResult() == 1){
                    jinEMxTwoList.clear();
                    List<MingXiBean.DataBean> data = response.getData();
//                    Collections.reverse(data);
                    Collections.sort(data, new Comparator<MingXiBean.DataBean>() {
                        @Override
                        public int compare(MingXiBean.DataBean o1, MingXiBean.DataBean o2) {

                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date dt1 = format.parse(o1.getTime());
                                Date dt2 = format.parse(o2.getTime());
                                if (dt1.getTime() > dt2.getTime()) {
                                    return 1;
                                } else if (dt1.getTime() < dt2.getTime()) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                    Collections.reverse(data);

                    jinEMxTwoList.addAll(data);
                    mingXiAdapter = new MingXiAdapter(MingXiActivity.this, jinEMxTwoList);
                    mMingxiListview.setAdapter(mingXiAdapter);
                }else {
                    Toast.makeText(MingXiActivity.this, "抱歉，暂时还没有交易！", Toast.LENGTH_SHORT).show();
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
