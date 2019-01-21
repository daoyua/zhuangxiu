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
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.FwDetailsFour;
import com.zx.zhuangxiu.model.FwDetailsThree;
import com.zx.zhuangxiu.model.FwDetailsTwo;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.model.ZhaoGRxqBean;
import com.zx.zhuangxiu.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 找工人的详情页；
 */
public class FoundWorkerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView worker_details_phone, worker_details_name, worker_details_time, worker_details_tiaojian,
            worker_details_gongzhong, worker_details_dizhi;
    private TextView worker_details_back;
    private CircleImageView worker_details_touxiang;

    private AlertDialog keFuDialog;

    private List<FwDetailsTwo> fwDetailsTwoList = new ArrayList<>();
    private List<FwDetailsThree> fwDetailsThreeList = new ArrayList<>();
    private List<FwDetailsFour> fwDetailsFourList = new ArrayList<>();
    private List<ZhaoGRxqBean.DataBean> mxqlist = new ArrayList<>();
    private int pkId;
    private String tetlephone;
    private TextView chat;
    private int id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_worker_details);

        Bundle bundle = getIntent().getExtras();
        pkId = bundle.getInt("pkId");

        initView();
        context=this;
    }

    private void initView() {
        worker_details_phone = (TextView) findViewById(R.id.worker_details_phone);
        worker_details_back = (TextView) findViewById(R.id.worker_details_back);
        worker_details_touxiang = (CircleImageView) findViewById(R.id.worker_details_touxiang);
        worker_details_name = (TextView) findViewById(R.id.worker_details_name);
        worker_details_time = (TextView) findViewById(R.id.worker_details_time);
        worker_details_tiaojian = (TextView) findViewById(R.id.worker_details_tiaojian);
        worker_details_gongzhong = (TextView) findViewById(R.id.worker_details_gongzhong);
        worker_details_dizhi = (TextView) findViewById(R.id.worker_details_dizhi);
        chat = findViewById(R.id.chat);

        chat.setOnClickListener(this);
        worker_details_phone.setOnClickListener(this);
        worker_details_back.setOnClickListener(this);
        worker_details_dizhi.setOnClickListener(this);



        getDetailsInfo();
    }

    private void getDetailsInfo() {
        String url = URLS.syFoundWorkerDetailsShow(pkId);
        Log.d("xxx", "找工人详情----url---------" + url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ZhaoGRxqBean>() {

            @Override
            public void onSuccess(ZhaoGRxqBean response) {
                if (response.getResult() == 1) {
                    mxqlist.add(response.getData());
                    id = response.getData().getUserId();
                    if (mxqlist.size() != 0) {
                        for (int i = 0; i < mxqlist.size(); i++) {
                            worker_details_name.setText("" + mxqlist.get(i).getRealName());
                            worker_details_time.setText("" + mxqlist.get(i).getRegisterDate());
//                                worker_details_tiaojian.setText(""+mxqlist.get(i).getRealname());
                            worker_details_gongzhong.setText("" + mxqlist.get(i).getWorkType());
                            worker_details_dizhi.setText("" + mxqlist.get(i).getAddress());

                            tetlephone = mxqlist.get(i).getTelenumber();


                            if (mxqlist.size() != 0) {
                                String imgUrl = mxqlist.get(i).getUserUrl();
                                Picasso.with(FoundWorkerDetailsActivity.this).load(URLS.HTTP + imgUrl).error(R.mipmap.logo_zhanwei).into(worker_details_touxiang);
                            }

                        }
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
            case R.id.worker_details_phone:
                showKefuDialog();
                break;
            case R.id.worker_details_back:
                this.finish();
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
            case R.id.worker_details_dizhi:
                Intent intents = new Intent(this, MapActivity.class);
                intents.putExtra("map",worker_details_dizhi.getText().toString());
//                intents.putExtra("lat",worker_details_dizhi.getl);
//                intents.putExtra("lon",worker_details_dizhi.getText().toString());
                startActivity(intents);

                break;

        }
    }


    private void showKefuDialog() {
        LayoutInflater inflater = LayoutInflater.from(FoundWorkerDetailsActivity.this);
        View view = inflater.inflate(R.layout.mypager_kefu_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(FoundWorkerDetailsActivity.this);
        builder.setView(view);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.kefu_dialog_quxiao);
        TextView tv_hujiao = (TextView) view.findViewById(R.id.kefu_dialog_hujiao);
        final TextView tv_dianhua = (TextView) view.findViewById(R.id.kefu_dialog_dianhua);

        tv_dianhua.setText(tetlephone);
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
