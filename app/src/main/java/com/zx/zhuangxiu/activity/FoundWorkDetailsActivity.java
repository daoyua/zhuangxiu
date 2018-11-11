package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.FWorkDetailsOne;
import com.zx.zhuangxiu.model.MydexinxiBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class FoundWorkDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView work_details_phone, work_details_mingcheng, work_details_buzhu, work_details_zhiwei, work_details_tiaojian, work_details_xueli,
            work_details_back,  work_details_gongzi, work_details_gongzhong, work_details_dizhi;
    private ImageView work_details_img;

    private AlertDialog keFuDialog;

    private int pkId;
    private List<FWorkDetailsOne.DataBean> fWorkDetailsTwoList = new ArrayList<>();
    private String address;
    private String telephone;
    private TextView chat;
    private GridView work_details_gridview;
    private GridviewworkdetailsAdapter gridviewworkdetailsAdapter;
    private List<String> list=new ArrayList<>();
    private TextView start_time;
    private TextView end_time;
    private int othXer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_work_details);

        Bundle bundle = getIntent().getExtras();
        pkId = bundle.getInt("pkId");
        address = bundle.getString("address");
        initView();
        context=this;
    }

    private void initView() {
        work_details_phone = (TextView) findViewById(R.id.work_details_phone);
        work_details_back = (TextView) findViewById(R.id.work_details_back);
        work_details_img = (ImageView) findViewById(R.id.work_details_img);
        work_details_mingcheng = (TextView) findViewById(R.id.work_details_mingcheng);
        work_details_buzhu = (TextView) findViewById(R.id.work_details_buzhu);
        work_details_zhiwei = (TextView) findViewById(R.id.work_details_zhiwei);
        work_details_tiaojian = (TextView) findViewById(R.id.work_details_tiaojian);
        work_details_xueli = (TextView) findViewById(R.id.work_details_xueli);
        work_details_gongzi = (TextView) findViewById(R.id.work_details_gongzi);
        work_details_gongzhong = (TextView) findViewById(R.id.work_details_gongzhong);
        work_details_dizhi = (TextView) findViewById(R.id.work_details_dizhi);
        start_time = (TextView) findViewById(R.id.start_time);
        end_time = (TextView) findViewById(R.id.end_time);

        work_details_gridview = (GridView)findViewById(R.id.founddetails_gridview);
        chat = findViewById(R.id.chat);
        chat.setOnClickListener(this);
        work_details_dizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(FoundWorkDetailsActivity.this, MapActivity.class);
                intents.putExtra("map",work_details_dizhi.getText().toString());
                startActivity(intents);

            }
        });



        work_details_phone.setOnClickListener(this);
        work_details_back.setOnClickListener(this);


        getWorkInfo();
//        gettupian();
    }



    private void getWorkInfo() {
        String url = URLS.syFoundWorkDetailsShow(pkId);
//        Log.d("xxx", "首页找工作详情----url---------"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<FWorkDetailsOne>() {
            @Override
            public void onSuccess(FWorkDetailsOne response) {
                if(response.getResult() == 1){
                    start_time.setText(response.getData().getStartTime());
                    end_time.setText(response.getData().getEndTime());

                    fWorkDetailsTwoList.add(response.getData());
                    if(fWorkDetailsTwoList.size() != 0){
                        othXer = response.getData().getUserId();
                        for (int i = 0; i<fWorkDetailsTwoList.size(); i++){
                            work_details_mingcheng.setText("项目名称："+fWorkDetailsTwoList.get(i).getName());
                            work_details_buzhu.setText("工作待遇："+fWorkDetailsTwoList.get(i).getTreatment());
                            work_details_zhiwei.setText(""+fWorkDetailsTwoList.get(i).getSynopsis());
                            work_details_tiaojian.setText(""+fWorkDetailsTwoList.get(i).getJobRequire());
                            work_details_xueli.setText(""+fWorkDetailsTwoList.get(i).getEducation());
                            work_details_gongzi.setText(""+fWorkDetailsTwoList.get(i).getWages());
                            work_details_dizhi.setText(""+address);
                            work_details_gongzhong.setText(""+fWorkDetailsTwoList.get(i).getWorktypes());
                            String imgUrl = fWorkDetailsTwoList.get(i).getImgUrl();
                            Picasso.with(FoundWorkDetailsActivity.this).load(URLS.HTTP+imgUrl).error(R.mipmap.logo_zhanwei).fit().into(work_details_img);
                            //获取电话号码
                            telephone = fWorkDetailsTwoList.get(i).getTelenumber();
                            //gridview 展示图片


                        }
                        list.addAll(Arrays.asList(response.getData().getDetailUrl().split(",")));
                        gridviewworkdetailsAdapter = new GridviewworkdetailsAdapter(FoundWorkDetailsActivity.this, list);
                        work_details_gridview.setAdapter(gridviewworkdetailsAdapter);

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
            case R.id.work_details_phone:
                showKefuDialog();
                break;
            case R.id.work_details_back:
                this.finish();
                break;
                case R.id.chat:
                    String url = URLS.mydata(othXer);
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
                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(othXer+"", userName, Uri.parse(userUrl)));
                            RongIM.getInstance().setMessageAttachedUserInfo(true);
                            RongIM.getInstance().startPrivateChat(context, othXer + "", "客服");
                        }

                        @Override
                        public void onFailure(Exception e) {

                        }
                    });

                    break;
        }
    }


    private void showKefuDialog() {
        LayoutInflater inflater = LayoutInflater.from(FoundWorkDetailsActivity.this);
        View view = inflater.inflate(R.layout.mypager_kefu_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(FoundWorkDetailsActivity.this);
        builder.setView(view);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.kefu_dialog_quxiao);
        TextView tv_hujiao = (TextView) view.findViewById(R.id.kefu_dialog_hujiao);
        final TextView tv_dianhua = (TextView) view.findViewById(R.id.kefu_dialog_dianhua);
        tv_dianhua.setText(telephone);
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
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_dianhua.getText()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        keFuDialog = builder.create();
        keFuDialog.setView(view, 0, 0, 0, 0);
        keFuDialog.show();
    }

}
