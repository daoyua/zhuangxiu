package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.YouHuiListViewAdapter;
import com.zx.zhuangxiu.model.YouhuihdBean;

import java.util.ArrayList;
import java.util.List;
/**
 * 首页-消息-个人通知
 * */
public class XiaoXiYouHuiActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView youhui_back;
    private ListView youhui_listview;

    private List<YouhuihdBean.DataBean> geRenTwoList = new ArrayList<>();
    private YouHuiListViewAdapter youHuiListViewAdapter;
    private TextView notext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi_you_hui);


        initView();
    }

    private void initView() {
        youhui_back = (TextView) findViewById(R.id.youhui_back);
        youhui_back.setOnClickListener(this);

        youhui_listview = (ListView)findViewById(R.id.youhui_listview);
        notext = (TextView) findViewById(R.id.youhui_notext);


        getInfo();
    }

    private void getInfo() {
        String url = URLS.youhuiShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<YouhuihdBean>() {
            @Override
            public void onSuccess(YouhuihdBean response) {
                if(response.getResult() == 1){
                    geRenTwoList.addAll(response.getData());
                    youHuiListViewAdapter = new YouHuiListViewAdapter(XiaoXiYouHuiActivity.this, geRenTwoList);
                    youhui_listview.setAdapter(youHuiListViewAdapter);
                }else {
                    notext.setVisibility(View.VISIBLE);
                    youhui_listview.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.youhui_back:
                this.finish();
                break;
        }

    }
}
