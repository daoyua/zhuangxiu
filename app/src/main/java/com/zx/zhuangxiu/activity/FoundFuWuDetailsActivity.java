package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.ImageAdapter;
import com.zx.zhuangxiu.model.DingdanBean;
import com.zx.zhuangxiu.model.FuWuDetailsTwo;
import com.zx.zhuangxiu.model.FuwuxiangqingBean;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.view.CircleImageView;
import com.zx.zhuangxiu.view.MylistView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class FoundFuWuDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView fuwu_details_name, fuwu_details_beizhu, fuwu_details_content, fuwu_details_goumai, fuwu_details_back;
    private ImageView fuwu_details_img;

    private List<FuWuDetailsTwo> fuWuDetailsTwoList = new ArrayList<>();
    private AlertDialog keFuDialog;
    private int pkId = 1;
    private List<FuwuxiangqingBean.DataBean> fuwulist = new ArrayList<>();
    private ImageView touxiang1;
    private TextView name1;
    private TextView dizhitext;
    private CircleImageView usertouxiang;
    private ImageView xiangmutu;
    private TextView chat;
    private List<DingdanBean.DataBean> dingdanlist = new ArrayList<>();
    private int paylogId;
    private double jiage;
    private String imageurls;
    private TextView mName;
    private TextView mPrice;
    private TextView mDetails;
    private String telenumber;
    private ImageView go_dianpu;
    private int id;
    private MylistView mlist;
    private List<String> list = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private TextView tv_dianpu;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_fu_wu_details);


        Bundle bundle = getIntent().getExtras();
        pkId = bundle.getInt("pkId");
        initView();
        context=this;

    }

    private void initView() {

        getInfo();

        fuwu_details_back = (TextView) findViewById(R.id.fuwu_details_back);//返回
//        fuwu_details_img = (ImageView) findViewById(R.id.fuwu_details_img);//
        fuwu_details_name = (TextView) findViewById(R.id.fuwu_details_name);//人名
        fuwu_details_beizhu = (TextView) findViewById(R.id.fuwu_details_beizhu);//
        fuwu_details_goumai = (TextView) findViewById(R.id.fuwu_details_goumai);//
        chat = (TextView) findViewById(R.id.chat);//
        touxiang1 = findViewById(R.id.fuwudetails_touxinag1);//公司头像
        name1 = findViewById(R.id.fuwudetails_name1);//公司名
        dizhitext = findViewById(R.id.fuwudetails_dizhi);//地址
        usertouxiang = findViewById(R.id.fuwudetails_image2);//人的头像
        xiangmutu = findViewById(R.id.fuwudetails_xiangmutu);
        mlist = findViewById(R.id.mlist);
        imageAdapter = new ImageAdapter(this, list);
        mlist.setAdapter(imageAdapter);

        go_dianpu = findViewById(R.id.go_dianpu);
        tv_dianpu = findViewById(R.id.tv_dianpu);
        go_dianpu.setVisibility(View.GONE);
        tv_dianpu.setVisibility(View.GONE);
        go_dianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoundFuWuDetailsActivity.this, FuWuShopActivity.class);
                intent.putExtra("UserID", id);
                startActivity(intent);
            }
        });

        mName = findViewById(R.id.name);
        mPrice = findViewById(R.id.price);
        mDetails = findViewById(R.id.details);
        chat.setOnClickListener(this);
        fuwu_details_goumai.setOnClickListener(this);
        fuwu_details_back.setOnClickListener(this);
        dizhitext.setOnClickListener(this);


    }

    private void getInfo() {
        String url = URLS.syFuWuDetailsShow(pkId);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<FuwuxiangqingBean>() {

            @Override
            public void onSuccess(FuwuxiangqingBean response) {
                if (response.getResult() == 1) {
                    if (response.getData().getUserId() != URLS.getUser_id()) {
                        go_dianpu.setVisibility(View.VISIBLE);
                        tv_dianpu.setVisibility(View.VISIBLE);

                        id = response.getData().getUserId();
                    }

                    String imgUrl = response.getData().getImgUrl();
                    if (imgUrl.contains(",") && !TextUtils.isEmpty(imgUrl)) {
                        String[] split = imgUrl.split(",");
                        list.addAll(Arrays.asList(split));
                        imageAdapter.notifyDataSetChanged();
                    } else {
                        list.add(imgUrl);
                        imageAdapter.notifyDataSetChanged();
                    }

                    name1.setText(response.getData().getNickname());//服务名称
                    dizhitext.setText(response.getData().getAddress());//服务地址
                    String userUrl = response.getData().getUserUrl();
                    if (userUrl.startsWith("http://")||userUrl.startsWith("https://")){
                        Picasso.with(FoundFuWuDetailsActivity.this).load( response.getData().getUserUrl()).error(R.mipmap.logo_zhanwei).fit().into(usertouxiang);//人的头像
                    }else {
                        Picasso.with(FoundFuWuDetailsActivity.this).load(URLS.HTTP + response.getData().getUserUrl()).error(R.mipmap.logo_zhanwei).fit().into(usertouxiang);//人的头像
                    }
                    fuwu_details_name.setText(response.getData().getNickname());//人名
//                    Picasso.with(FoundFuWuDetailsActivity.this).load(URLS.HTTP + response.getData().getImgUrl()).error(R.mipmap.logo_zhanwei).fit().into(xiangmutu);//项目图片
                    imageurls = response.getData().getImgUrl();
                    jiage = response.getData().getPrice();
                    mPrice.setText(response.getData().getPrice() + "元");
                    mName.setText(response.getData().getName());
                    mDetails.setText(response.getData().getRequires());
                    fuwulist.add(response.getData());
                    telenumber = response.getData().getTelenumber();
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
            case R.id.fuwu_details_back:
                this.finish();
                break;
            case R.id.fuwu_details_goumai:
                showKefuDialog(telenumber);

                break;
            case R.id.chat:

                String url = URLS.mydata(id);
                OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MydexinxiBean>() {

                    private String userUrl;
                    private String userName;

                    @Override
                    public void onSuccess(MydexinxiBean response) {
                        userName = response.getData().getNickname();
                        userUrl = response.getData().getUserUrl();
                        if (!userUrl.startsWith("http://") && !userUrl.startsWith("https://")) {
                            userUrl = URLS.HTTP + userUrl;
                        }
                        URLS.setYOUR(userName);
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(id+"", userName, Uri.parse(userUrl)));
                        RongIM.getInstance().setMessageAttachedUserInfo(true);
                        RongIM.getInstance().startPrivateChat(context, id + "", "客服");
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
                break;
            case R.id.fuwudetails_dizhi:
                Intent intents = new Intent(this, MapActivity.class);
                intents.putExtra("map", dizhitext.getText().toString());
//                intents.putExtra("lat",);
//                intents.putExtra("lon", );
                startActivity(intents);
                break;
        }
    }

    private void showKefuDialog(final String m) {
        View view = View.inflate(this, R.layout.mypager_kefu_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(FoundFuWuDetailsActivity.this);
        builder.setView(view);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.kefu_dialog_quxiao);
        TextView tv_hujiao = (TextView) view.findViewById(R.id.kefu_dialog_hujiao);
        TextView tv_dianhua = (TextView) view.findViewById(R.id.kefu_dialog_dianhua);

        tv_dianhua.setText("" + m);

        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keFuDialog.dismiss();
            }
        });
        tv_hujiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keFuDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + m));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        keFuDialog = builder.create();
        keFuDialog.setView(view, 0, 0, 0, 0);
        keFuDialog.show();

    }

}
