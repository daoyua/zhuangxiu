package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.DingDanQYandSJAdapter;
import com.zx.zhuangxiu.model.MyDingDanQYandSJ;
import com.zx.zhuangxiu.model.MyDingDanQYandSJOne;

import java.util.ArrayList;
import java.util.List;

public class MyDingDanQYandSJActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private RadioGroup mRadioGroup;
    private RadioButton mRb1, mRb2;
    private DingDanQYandSJAdapter adapter;
    private List<MyDingDanQYandSJOne>list= new ArrayList<>();
    private TextView my_dingdan_back;
    //适配器写了，实体类建立了。产品详情就让他跳转处理订单，传过去数据地址和收货人的姓名电话，填写快递号和快递种类
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dan_qyand_sj);
        initView();

    }
    private void initView() {
        mRb1 = (RadioButton) findViewById(R.id.my_dingdan_rb1);
        mRb2 = (RadioButton) findViewById(R.id.my_dingdan_rb2);
        mRadioGroup = (RadioGroup) findViewById(R.id.my_dingdan_rg);
        my_dingdan_back=(TextView)findViewById(R.id.my_dingdan_back);
        my_dingdan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.my_dingdan_listview);
        init("0");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_dingdan_rb1:
                list.clear();
                init("0");
               //未支付的订单
                break;
            case R.id.my_dingdan_rb2:
               //已支付的的订单
                list.clear();
                init("1");
                break;

        }
    }

    public  void init(String stuta){
        String url= URLS.dingdanqiyeandshangjia(URLS.getUser_id(),stuta);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyDingDanQYandSJ>() {
            @Override
            public void onSuccess(MyDingDanQYandSJ response) {
                if (response.getId()==0){
                    list=response.getData();
                    adapter=  new DingDanQYandSJAdapter(MyDingDanQYandSJActivity.this,list);
                    listview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).getExpress().equals("0")||list.get(i).getExpress()==null){
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(MyDingDanQYandSJActivity.this,MyDingDanQYandSJXiangQingAcitivity.class);
                    bundle.putString("name",list.get(i).getJskPersonAddress().get(0).getUserName());
                    bundle.putString("dizhi",list.get(i).getJskPersonAddress().get(0).getAddress());
                    bundle.putString("phone",list.get(i).getJskPersonAddress().get(0).getMobile());
                    bundle.putString("pkid",list.get(i).getJskPersonOrderDetail().get(0).getOrderId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }
}
