package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.WlBean;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage;
    /**
     * 房屋装修
     */
    private TextView mName;
    /**
     * 50*50
     */
    private TextView mSimple;
    /**
     * ¥1000
     */
    private TextView mMoney;
    /**
     * x1
     */
    private TextView mNum;
    /**
     * ¥1000
     */
    private TextView mTvRmoney;
    /**
     * 2018-5-3
     */
    private TextView mTvTime;
    /**
     * 201807003
     */
    private TextView mTvNumber;
    /**
     * 物流详情
     */
    private TextView mWuliu;
    /**
     * 201807003
     */
    private TextView mWlName;
    /**
     * 201807003
     */
    private TextView mWlNumber;
    private TextView mAllmoney;
    /**
     * 确认收货
     */
    private TextView mTvOk;
    private TextView mBack;
    private int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId", -1);
        initView();
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.image);
        mBack = (TextView) findViewById(R.id.back);
        mName = (TextView) findViewById(R.id.name);
        mSimple = (TextView) findViewById(R.id.simple);
        mMoney = (TextView) findViewById(R.id.money);
        mAllmoney = (TextView) findViewById(R.id.tv_allmoney);
        mNum = (TextView) findViewById(R.id.num);
        mTvRmoney = (TextView) findViewById(R.id.tv_rmoney);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvNumber = (TextView) findViewById(R.id.tv_number);
        mWuliu = (TextView) findViewById(R.id.wuliu);
        mWlName = (TextView) findViewById(R.id.wl_name);
        mWlNumber = (TextView) findViewById(R.id.wl_number);
        mTvOk = (TextView) findViewById(R.id.tv_ok);
        mBack.setOnClickListener(this);
        mTvOk.setOnClickListener(this);

        load();
    }

    private void load() {
        OkHttpUtils.get(URLS.HTTP + "/api/order/getOrderInfo?userId=" +URLS.getUser_id() + "&orderId=" + orderId, new OkHttpUtils.ResultCallback<WlBean>() {
            @Override
            public void onSuccess(WlBean response) {
                if (response.getResult() == 1) {
                    WlBean.DataBean.OrderItemsBean orderItemsBean = response.getData().getOrderItems().get(0);
                    mName.setText(orderItemsBean.getCdname());
                    String infoName = orderItemsBean.getInfoName();
                    if (TextUtils.isEmpty(infoName)) {
                        mSimple.setText("");
                    } else {
                        mSimple.setText(infoName);
                    }
                    WlBean.DataBean data = response.getData();
                    mMoney.setText(orderItemsBean.getBuyprice()+"");
                    mNum.setText("x" + orderItemsBean.getCount());
                    mAllmoney.setText("¥"+data.getOrder().getTotalprice());
                    mTvTime.setText(data.getOrder().getAddTime());
                    mTvNumber.setText(data.getOrder().getOrderno());
                    String delivercode = data.getOrder().getDelivercode();
                    if (TextUtils.isEmpty(delivercode)){
                        mWlNumber.setText("");
                    }else {
                        mWlNumber.setText(delivercode);
                    }
                    String delivername = data.getOrder().getDelivername();
                    if (TextUtils.isEmpty(delivername)){
                        mWlName.setText("暂无物流信息");
                    }else {
                        mWlName.setText(delivername);
                    }
                    Picasso.with(OrderActivity.this).load(URLS.HTTP + orderItemsBean.getPicture()).placeholder(R.mipmap.logo_zhanwei).error(R.mipmap.logo_zhanwei).fit().into(mImage);
                  /*  if (data.getOrder().getDeliverstatus()!=0){
                        mTvOk.setVisibility(View.VISIBLE);
                    }else {
                        mTvOk.setVisibility(View.GONE);
                    }*/
                    if (data.getOrder().getOrderstatus()==5||data.getOrder().getOrderstatus()==4||data.getOrder().getOrderstatus()==6||data.getOrder().getOrderstatus()==7){
                        mTvOk.setVisibility(View.GONE);
                    }else if (data.getOrder().getOrderstatus()==2||data.getOrder().getOrderstatus()==3){
                        mTvOk.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(OrderActivity.this, "暂无该订单信息", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:

                break;
            case R.id.back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.tv_ok:
                A(orderId);
                break;
        }
    }

    public void A(int oederID) {
        OkHttpUtils.get(URLS.HTTP + "/api/order/conformOrder?orderId=" + oederID, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {
                if (response.getResult() == 1) {
                    Toast.makeText(OrderActivity.this, "确认收货成功", Toast.LENGTH_SHORT).show();
                    load();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
