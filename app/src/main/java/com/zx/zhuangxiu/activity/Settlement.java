package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.ShopAdapter;
import com.zx.zhuangxiu.model.JiiesuandizhiBean;
import com.zx.zhuangxiu.model.OrderBean;
import com.zx.zhuangxiu.model.OrderEntity;
import com.zx.zhuangxiu.model.ZhifubaoBean;

import java.util.List;

import okhttp3.FormBody;

public class Settlement extends AppCompatActivity implements View.OnClickListener {

    private TextView jiesuan_ren;
    private TextView jiesuan_dizhi;
    private TextView jiesuan_shangpinzonge;
    private ListView mListview;
    private OrderEntity order;
    private List<OrderEntity.DataBean.ListBean> list;
    private Button btn_zhifu;
    private String id;
    private TextView mBack;
    private int addrssID = -1;
    private TextView addAddress;
    private LinearLayout linearLayout;
    private RadioButton radiozhifubao;
    private RadioButton radioweixin;
    int payTYpe =2;
    private LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        order = (OrderEntity) getIntent().getSerializableExtra("order");
        id = getIntent().getStringExtra("id");
        list = order.getData().getList();
        initView();
        getaddress();
    }

    private void initView() {
        jiesuan_ren = findViewById(R.id.jiesuan_ren);
        jiesuan_dizhi = findViewById(R.id.jiesuan_dizhi);
        mBack = findViewById(R.id.jiesuan_back);
        mListview = findViewById(R.id.listview);
        btn_zhifu = findViewById(R.id.jiesuan_zhifu);
        addAddress = findViewById(R.id.addAddress);
        linearLayout = findViewById(R.id.lin);
        jiesuan_shangpinzonge = findViewById(R.id.jiesuan_shangpinzonge);
        jiesuan_shangpinzonge.setText(order.getData().getTotalMoney()+"元");
        radiozhifubao = (RadioButton) findViewById(R.id.radiozhifubao);
        radioweixin = (RadioButton) findViewById(R.id.radioweixin);

        linearLayout.setOnClickListener(this);

        ShopAdapter shopAdapter = new ShopAdapter(list, this);
        mListview.setAdapter(shopAdapter);
        btn_zhifu.setOnClickListener(this);
        mBack.setOnClickListener(this);
        addAddress.setOnClickListener(this);

        //        支付宝支付订单
        radiozhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payTYpe = 2;
                }
            }
        });
//        微信支付
        radioweixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payTYpe = 3;
                }
            }
        });



    }

    /**
     * 获取地址
     */
    private void getaddress() {
        String url = URLS.jiesuandizhi(URLS.getUser_id(), 1);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<JiiesuandizhiBean>() {
            @Override
            public void onSuccess(JiiesuandizhiBean response) {

                if (response.getResult() == 1) {
                    List<JiiesuandizhiBean.DataBean> data = response.getData();
                    if (data.size()>0){
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getStatus() == 1) {
                                linearLayout.setVisibility(View.VISIBLE);
                                addAddress.setVisibility(View.GONE);
                                jiesuan_ren.setText(data.get(i).getName());
                                jiesuan_dizhi.setText(data.get(i).getProvince() + data.get(i).getCity() + data.get(i).getCounty() + data.get(i).getDetaladdress());
                                addrssID = data.get(i).getId();
                                break;
                            }
                        }
                    }else {
                        addAddress.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    private void shopOrder(String id, int addressID) {
        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("shopCardIds", id)
                .add("addressId", addressID + "")
                .build();

        OkHttpUtils.post(URLS.shoppOrder(), formBody, new OkHttpUtils.ResultCallback<OrderBean>() {
            @Override
            public void onSuccess(OrderBean response) {

                getjiesuan(response.getData().getPaylogId(),payTYpe);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void getjiesuan(int paylogId, final int payTYpe) {
        String urla = URLS.jiesaun(paylogId, payTYpe);
        OkHttpUtils.get(urla, new OkHttpUtils.ResultCallback<ZhifubaoBean>() {//复用实体类
            @Override
            public void onSuccess(ZhifubaoBean response) {
                if (response.getResult() == 1) {
                    if (payTYpe==2){
                        Intent intent = new Intent(Settlement.this, AilPayActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    }else if (payTYpe==3){
                        Intent intent = new Intent(Settlement.this, WeiXinZFActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    }



                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jiesuan_zhifu:
                if (addrssID != -1) {
                    shopOrder(id, addrssID);
                } else {
                    Toast.makeText(this, "请先添加收货地址", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jiesuan_back:
                finish();
                break;
            case R.id.lin:
                Intent intent=new Intent(Settlement.this,MylookaddressActivity.class);
                startActivityForResult(intent,300);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                getaddress();
                break;
            case 300:
                if (data!=null){
                    String name = data.getStringExtra("name");
                    String address = data.getStringExtra("address");
                    int addressids = data.getIntExtra("addressid",0);
                    jiesuan_dizhi.setText(""+address);
                    jiesuan_ren.setText(""+name+"       ");
                }

                break;
        }
    }
}
