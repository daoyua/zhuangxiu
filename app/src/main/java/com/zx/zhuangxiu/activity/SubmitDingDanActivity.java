package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.SubmitDdListviewAdapter;
import com.zx.zhuangxiu.model.SubmitDdOne;
import com.zx.zhuangxiu.model.SubmitDdThree;
import com.zx.zhuangxiu.model.SubmitDdTwo;

import java.util.ArrayList;
import java.util.List;

public class SubmitDingDanActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView sDingdan_back, sDingdan_price;
    private ListView sDingdan_listview;
    private Button sDingdan_tijiao;
    private SubmitDdListviewAdapter submitDdListviewAdapter;

    private List<SubmitDdThree> submitDdThreeList = new ArrayList<>();
    private SubmitDdTwo submitDdTwo = new SubmitDdTwo();

    private String shopId = "";
    private float heji = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_ding_dan);

        Bundle bundle = getIntent().getExtras();
        shopId = bundle.getString("pkId");
        heji = bundle.getFloat("heji");


        Log.d("jjj", "确认订单的shopId-------------"+shopId+"------------总价格合计---------"+heji );

        initView();
    }

    private void initView() {
        sDingdan_back = (TextView)findViewById(R.id.sDingdan_back);
        sDingdan_back.setOnClickListener(this);

        sDingdan_listview = (ListView)findViewById(R.id.sDingdan_listview);

        sDingdan_price = (TextView)findViewById(R.id.sDingdan_price);
        sDingdan_price.setText("合计:"+heji);

        sDingdan_tijiao = (Button)findViewById(R.id.sDingdan_tijiao);
        sDingdan_tijiao.setOnClickListener(this);

        getDingDanList();

    }

    private void getDingDanList() {
            String url = URLS.submitDdShow(URLS.getUser_id(), 2, shopId);
        Log.d("jjj", "确认订单的url-------------"+url );
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SubmitDdOne>() {
            @Override
            public void onSuccess(SubmitDdOne response) {
//                Log.d("jjj", "确认订单的response-------------"+response );
                if(response.getStatus() == 0){
                    submitDdTwo = response.getData();
                    submitDdThreeList = response.getData().getOrderDetailList();
//                    Log.d("jjj", "确认订单的submitDdThreeList-------------"+submitDdThreeList );
                    if(submitDdThreeList.size() != 0){
                        submitDdListviewAdapter = new SubmitDdListviewAdapter(SubmitDingDanActivity.this, submitDdThreeList);
                        sDingdan_listview.setAdapter(submitDdListviewAdapter);
                    }else {
                        Toast.makeText(SubmitDingDanActivity.this, "您暂时还没有订单，赶紧去购物车添加吧!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("jjj", "确认订单的-------e------"+e );
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sDingdan_back:
                this.finish();
                break;
            case R.id.sDingdan_tijiao:
                tiJiaoDdInfo();
                break;
        }
    }

    private void tiJiaoDdInfo() {
        String url = URLS.tiJiaoDdShow(submitDdTwo);
        Log.d("jjj", "提交确认订单的-------url------"+ url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
