package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.DingdanBean;
import com.zx.zhuangxiu.model.JiiesuandizhiBean;
import com.zx.zhuangxiu.model.WeiXinOne;
import com.zx.zhuangxiu.model.WeiXinTwo;
import com.zx.zhuangxiu.model.ZhifubaoBean;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.List;

import okhttp3.FormBody;

public class JieSuanActivity extends AppCompatActivity implements View.OnClickListener {

    private String dingdan;
    private float heji = 0;

    private TextView jiesuan_back;
    private TextView jiesuan_dizhi, jiesuan_money, jiesuan_ren;
    private Button jiesuan_zhifu;

    private RadioButton radiozhifubao, radioweixin;

    private WeiXinTwo weiXinTwo = new WeiXinTwo();

    private String address;
    private String addressname;
    private int pikid;//地址ID

    private LinearLayout ll1;
    private int paylogId;
    private double jiage;
    private ImageView wupinimage;
    private TextView wupinname, jiesuan_shangpinzonge, mAdd;
    private TextView wupinnumtext;
    private TextView wupinpricetext;
    private String imageurols;
    private int addressID;
    int payTYpe = 2;
    private int productId;
    private int attributeId;
    private int count;
    private double jiage1;
    private String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan);
        Intent bundle = getIntent();
        jiage1 = bundle.getDoubleExtra("jiage", -1);
        image = bundle.getStringExtra("image");
        productId = bundle.getIntExtra("productId", -1);
        attributeId = bundle.getIntExtra("attributeId", -1);
        count = bundle.getIntExtra("count", -1);
        initView();


    }

    private void initView() {
        jiesuan_back = (TextView) findViewById(R.id.jiesuan_back);
        jiesuan_back.setOnClickListener(this);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll1.setOnClickListener(this);
        jiesuan_ren = (TextView) findViewById(R.id.jiesuan_ren);
        jiesuan_dizhi = (TextView) findViewById(R.id.jiesuan_dizhi);
        jiesuan_money = (TextView) findViewById(R.id.jiesuan_money);
        jiesuan_money.setText("合计: ￥" + jiage1*count);
        jiesuan_zhifu = (Button) findViewById(R.id.jiesuan_zhifu);
        jiesuan_zhifu.setOnClickListener(this);
        radiozhifubao = (RadioButton) findViewById(R.id.radiozhifubao);
        radioweixin = (RadioButton) findViewById(R.id.radioweixin);
        wupinimage = findViewById(R.id.jiesuan_wupinimage);//物品图像
        wupinname = findViewById(R.id.jiesuan_wupinname); //物品名称
        wupinnumtext = findViewById(R.id.jiesuan_wupinnum);//物品数量
        wupinnumtext.setText("*"+count);
        wupinpricetext = findViewById(R.id.jiesuan_wupinprice);//物品价格
        jiesuan_shangpinzonge = findViewById(R.id.jiesuan_shangpinzonge);//物品价格
        mAdd = findViewById(R.id.add);
        wupinpricetext.setText(jiage1 + "元");
        jiesuan_shangpinzonge.setText(jiage1*count + "元");
        wupinpricetext.setText(jiage1*count + "元");
        Picasso.with(this).load(URLS.HTTP + image).error(R.mipmap.logo_zhanwei).fit().into(wupinimage);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转地址列表
                Intent intent1 = new Intent(JieSuanActivity.this, MylookaddressActivity.class);
                startActivityForResult(intent1, 100);

            }
        });
        radiozhifubao.setChecked(true);
        getaddress();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                getaddress();
                break;
            case 300:
                if (data != null) {
                    String name = data.getStringExtra("name");
                    String address = data.getStringExtra("address");
                    addressID = data.getIntExtra("addressid", 0);
                    jiesuan_dizhi.setText("" + address);
                    jiesuan_ren.setText("" + name + "");
                }

                break;
        }
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
                    if (response.getData().size() > 0) {
                        mAdd.setVisibility(View.GONE);
                        ll1.setVisibility(View.VISIBLE);
                        List<JiiesuandizhiBean.DataBean> data = response.getData();
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getStatus() == 1) {
                                addressID = data.get(i).getId();
                                String sheng = data.get(i).getProvince();//省
                                String shi = data.get(i).getCity();//市
                                String xian = data.get(i).getCounty();//县
                                String xiangxiaddress = data.get(i).getDetaladdress();//详细地址
                                jiesuan_dizhi.setText(sheng + shi + xian + xiangxiaddress);
                                jiesuan_ren.setText(data.get(i).getName());
                                break;
                            }
                        }
//                        getshengchengdingdna();

                    } else {
                        mAdd.setVisibility(View.VISIBLE);
                        ll1.setVisibility(View.GONE);
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
        switch (view.getId()) {
            case R.id.jiesuan_back:
                this.finish();
                break;
            case R.id.jiesuan_zhifu:
                if (!jiesuan_dizhi.getText().equals("")) {
                    getOrder(addressID, productId, attributeId, count);
                } else {
                    Toast.makeText(JieSuanActivity.this, "请先添加默认收货地址", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.ll1:
                //跳转地址列表
                Intent intent1 = new Intent(JieSuanActivity.this, MylookaddressActivity.class);
                startActivityForResult(intent1, 300);
                break;
        }
    }

    public void getOrder(int address, int productid, int attributeId, int count) {
        String url = URLS.zhijiegoumai();
        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("addressId", address + "")
                .add("productId", productid + "")
                .add("attributeId", attributeId + "")
                .add("count", count + "").build();
        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<DingdanBean>() {
            @Override
            public void onSuccess(DingdanBean response) {
                if (response.getResult() == 1) {
                    DingdanBean.DataBean data = response.getData();
                    paylogId = data.getPaylogId();
                    getjiesuan(payTYpe);
                } else {
                    ToastUtil.showShort(getApplicationContext(), "添加失败！！！");
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "请求失败！！！");
            }
        });
    }

    private void getjiesuan(final int payTYpe) {
        String urla = URLS.jiesaun(paylogId, payTYpe);
        OkHttpUtils.get(urla, new OkHttpUtils.ResultCallback<ZhifubaoBean>() {//复用实体类
            @Override
            public void onSuccess(ZhifubaoBean response) {
                if (response.getResult() == 1) {
                    if (payTYpe == 2) {
                        Intent intent = new Intent(JieSuanActivity.this, AilPayActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    } else if (payTYpe == 3) {
                        Intent intent = new Intent(JieSuanActivity.this, WeiXinZFActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    }


                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(JieSuanActivity.this, "支付失败哦", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
