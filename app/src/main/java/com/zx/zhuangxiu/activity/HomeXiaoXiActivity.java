package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.XiaoxishuliangBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-消息页面
 */
public class HomeXiaoXiActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mHomeXiaoXi, home_xiaoxi_youhui;
    private TextView home_xiaoxi_back;
    private LinearLayout home_xiaoxi_tuisong;
    private List<XiaoxishuliangBean.DataBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_xiao_xi);

        initView();
        getxiaoxishuliang();//获得消息数量
    }

    private void initView() {
        mHomeXiaoXi = (LinearLayout) findViewById(R.id.home_xiaoxi_tongzhi);
        home_xiaoxi_back = (TextView) findViewById(R.id.home_xiaoxi_back);
        home_xiaoxi_youhui = (LinearLayout) findViewById(R.id.home_xiaoxi_youhui);
        home_xiaoxi_tuisong = findViewById(R.id.home_xiaoxi_tuisong);


        home_xiaoxi_youhui.setOnClickListener(this);
        mHomeXiaoXi.setOnClickListener(this);
        home_xiaoxi_back.setOnClickListener(this);
        home_xiaoxi_tuisong.setOnClickListener(this);
    }


    private void getxiaoxishuliang(){

        String url = URLS.getxiaoxishuliang();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<XiaoxishuliangBean>() {
            @Override
            public void onSuccess(XiaoxishuliangBean response) {

                if (response.getResult() == 1){
                    mlist.add(response.getData());
                    for (int i = 0; i < mlist.size(); i++) {
                            int xitongnum = mlist.get(i).getSystem();
                            int youhuinum = mlist.get(i).getGuanzhu();
                            int tuisongnum = mlist.get(i).getGuanzhu();
                    }
                }
            }
            @Override
            public void onFailure(Exception e) {

            }
        });

    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.home_xiaoxi_tongzhi:
                intent.setClass(HomeXiaoXiActivity.this, XiaoXiNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.home_xiaoxi_youhui:
                intent.setClass(HomeXiaoXiActivity.this, XiaoXiYouHuiActivity.class);
                startActivity(intent);
                break;
            case R.id.home_xiaoxi_tuisong:
                intent.setClass(HomeXiaoXiActivity.this, XiaoXiYouHuiActivity.class);
                startActivity(intent);
                break;
            case R.id.home_xiaoxi_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
