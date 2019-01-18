package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.GoodsViewpagerAdapter;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.model.ShopFreeBean;
import com.zx.zhuangxiu.model.ZhifubaoBean;
import com.zx.zhuangxiu.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页-找产品
 */
public class FoundGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout mGoodsTab;
    private ViewPager goodsViewPager;
    private List<String> mList = new ArrayList<String>();
    private GoodsViewpagerAdapter goodsViewpagerAdapter;

    private ImageView goods_search;
    private TextView goods_back;
    private List<DaxiaofenleiBean.DataBean> mlist = new ArrayList<>();
    private MyPopupWindow mPopupWindow;
    private TextView foundgood_fabu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_goods);
        initView();
        getfenlei();
    }

    private void getfenlei() {

        String url = URLS.getdaxiaolei(5);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DaxiaofenleiBean>() {
            @Override
            public void onSuccess(DaxiaofenleiBean response) {

                if (response.getResult() == 1) {
                    mlist.addAll(response.getData());
                    //tablayout和viewpager关联
                    mGoodsTab.setupWithViewPager(goodsViewPager);
                    goodsViewpagerAdapter = new GoodsViewpagerAdapter(getSupportFragmentManager(), mlist);
                    goodsViewPager.setAdapter(goodsViewpagerAdapter);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    private void initView() {
        goods_back = (TextView) findViewById(R.id.goods_back);
        goods_search = (ImageView) findViewById(R.id.goods_search);
        foundgood_fabu = findViewById(R.id.foundgood_fabu);
        foundgood_fabu.setOnClickListener(this);
        goods_back.setOnClickListener(this);
        goods_search.setOnClickListener(this);

        mGoodsTab = (TabLayout) findViewById(R.id.goods_tab);
        goodsViewPager = (ViewPager) findViewById(R.id.goods_viewpager);
        mGoodsTab.setupWithViewPager(goodsViewPager);
       /* //tab的标题
        mList.add("装修建材");
        mList.add("家政服务");
        mList.add("其他");

        //tablayout和viewpager关联
        mGoodsTab.setupWithViewPager(goodsViewPager);
        goodsViewpagerAdapter = new GoodsViewpagerAdapter(getSupportFragmentManager(), mList);
        goodsViewPager.setAdapter(goodsViewpagerAdapter);*/
//TODO

    }
boolean dubug=true;//测试发布产品
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.goods_back:
                this.finish();
                break;
            case R.id.foundgood_fabu:

                if(dubug){
                    intent.setClass(FoundGoodsActivity.this, FabuSpActivity.class);
                    startActivity(intent);
                    return;
                }
                if(Constants.allow==-1){
                    germyneirong();
                    break;
                }
                if (Constants.allow == 0) {
                    showPopWindow();
                } else {
                    intent.setClass(FoundGoodsActivity.this, FabuSpActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.goods_search:
                intent.setClass(FoundGoodsActivity.this, SouSuoActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void germyneirong() {

        String url = URLS.mydata(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MydexinxiBean>() {
            @Override
            public void onSuccess(MydexinxiBean response) {


                if (response.getResult() == 1) {

                    MydexinxiBean.DataBean data = response.getData();
                    Constants.allow = data.getAllow();
                    //TODO
//                    if (data.getUserType() == 1) {
//                        Toast.makeText(foudgood)
//                        foundgood_fabu.setVisibility(View.GONE);
//                    } else {
//                        foundgood_fabu.setVisibility(View.VISIBLE);
//                    }
                    if (Constants.allow == 0) {
                        showPopWindow();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(FoundGoodsActivity.this, FabuSpActivity.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
    }

    private String paylogId;
    private void showPopWindow() {

        View inflate = View.inflate(FoundGoodsActivity.this, R.layout.pop_shop, null);
        TextView mCancel = inflate.findViewById(R.id.cancel);
        Button mAi_pay = inflate.findViewById(R.id.btn_alipay);
        Button mWx_PAY = inflate.findViewById(R.id.btn_wxpay);
        mPopupWindow = new MyPopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(FoundGoodsActivity.this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
       setBackgroundAlpha(0.3f);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mAi_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                        .build();
                OkHttpUtils.post(URLS.shopFee(), formBody, new OkHttpUtils.ResultCallback<ShopFreeBean>() {


                    @Override
                    public void onSuccess(ShopFreeBean response) {
                        ShopFreeBean.DataBean data = response.getData();
                        paylogId = data.getPaylogId();
                        /*  支付宝支付*/
                        getjiesuan(2);
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });


            }
        });
        mWx_PAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                        .build();
                OkHttpUtils.post(URLS.shopFee(), formBody, new OkHttpUtils.ResultCallback<ShopFreeBean>() {
                    @Override
                    public void onSuccess(ShopFreeBean response) {
                        ShopFreeBean.DataBean data = response.getData();
                        paylogId = data.getPaylogId();
                        /*  微信*/
                        getjiesuan(3);
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });

            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }
    private void getjiesuan(final int payTYpe) {
        String urla = URLS.jiesaun(Integer.parseInt(paylogId), payTYpe);
        OkHttpUtils.get(urla, new OkHttpUtils.ResultCallback<ZhifubaoBean>() {//复用实体类
            @Override
            public void onSuccess(ZhifubaoBean response) {
                if (response.getResult() == 1) {
                    mPopupWindow.dismiss();
                    if (payTYpe == 2) {
                        Intent intent = new Intent(FoundGoodsActivity.this, AilPayActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    } else if (payTYpe == 3) {
                        Intent intent = new Intent(FoundGoodsActivity.this, WeiXinZFActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(FoundGoodsActivity.this, "支付失败哦", Toast.LENGTH_SHORT).show();
                    mPopupWindow.dismiss();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(FoundGoodsActivity.this, "支付失败哦", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });

    }

}
