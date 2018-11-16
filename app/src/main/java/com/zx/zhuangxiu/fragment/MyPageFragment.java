package com.zx.zhuangxiu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.AilPayActivity;
import com.zx.zhuangxiu.activity.FuWuShopActivity;
import com.zx.zhuangxiu.activity.JinEActivity;
import com.zx.zhuangxiu.activity.MyDianPuActivity;
import com.zx.zhuangxiu.activity.MyDingDansActivity;
import com.zx.zhuangxiu.activity.MyDongTaiGRActivity;
import com.zx.zhuangxiu.activity.MyDongTaiQYActivity;
import com.zx.zhuangxiu.activity.MyInfoActivity;
import com.zx.zhuangxiu.activity.Subconversationlist;
import com.zx.zhuangxiu.activity.WeiXinZFActivity;
import com.zx.zhuangxiu.model.MyDataOne;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.model.ShopFreeBean;
import com.zx.zhuangxiu.model.ZhifubaoBean;
import com.zx.zhuangxiu.view.CircleImageView;
import com.zx.zhuangxiu.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import okhttp3.FormBody;

import static android.app.Activity.RESULT_OK;

/**
 * 我的，分为三个不同端，企业、商家 、个人
 * 修改时间：2018年7月25日19:52。
 * 修改人：徐康康
 * 源代码著作人：徐督佳
 */
public class MyPageFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mDingdan, mInfo, mKefu, mydongtai, myqiye, mydianpu, myjifenshangcheng, zaixiankefu, mypager_dizhi, sethoutai;
    private LinearLayout mLlMoney, my_jifen;
    private TextView mypager_name, mypager_phone, mypager_money, mypager_jifen;
    private List<MyDataOne> list = new ArrayList<>();
    private AlertDialog keFuDialog;
    private CircleImageView mypager_touxiang;

    private int type;
    private MyPopupWindow mPopupWindow;
    private String paylogId;
    private int allow;
    private TextView message_num;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what > 0 && msg.what <= 99) {
                message_num.setVisibility(View.VISIBLE);
                message_num.setText(msg.what + "");
            } else if (msg.what > 99) {
                message_num.setVisibility(View.VISIBLE);
                message_num.setText(msg.what + "");
            } else if (msg.what == 0) {
                message_num.setVisibility(View.GONE);
            }

        }
    };
    private SmartRefreshLayout mRefresh;
    private RelativeLayout dianpu;
    private TextView mypage_version;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, container, false);

//        getwodeyemian();
        initView(view);
        germyneirong();
        RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new RongIM.OnReceiveUnreadCountChangedListener() {
            @Override
            public void onMessageIncreased(int i) {
                Message obtain = Message.obtain();
                obtain.what = i;
                handler.sendMessage(obtain);
                Log.e("TAG", i + "");

            }
        }, Conversation.ConversationType.PRIVATE);
        return view;
    }

    ///////////////////////////////////////////这个方法是为了获取我的页面展示的东西///////////////////////////////////////////

    private void germyneirong() {

        String url = URLS.mydata(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MydexinxiBean>() {
            @Override
            public void onSuccess(MydexinxiBean response) {

                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {

                    MydexinxiBean.DataBean data = response.getData();
                    type = data.getUserType();
                    mypager_name.setText(data.getNickname());
                    String telenumber = data.getTelenumber();
                    if (!TextUtils.isEmpty(telenumber) && telenumber.length() == 11)
                        mypager_phone.setText(telenumber.substring(0, 3) + "****" + telenumber.substring(7, telenumber.length()));
                    String userUrl = data.getUserUrl();
                    if (data.getUserUrl().startsWith("http://") || data.getUserUrl().startsWith("https://")) {
                        Picasso.with(getActivity())
                                .load(data.getUserUrl())
                                .placeholder(R.mipmap.logo_zhanwei)//logo_zhanwei
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mypager_touxiang);

                    } else {
                        Picasso.with(getActivity())
                                .load(URLS.HTTP + data.getUserUrl())
                                .placeholder(R.mipmap.logo_zhanwei)
                                .error(R.mipmap.logo_zhanwei)
                                .fit()
                                .centerCrop()
                                .into(mypager_touxiang);

                    }

                    mypager_money.setText(data.getBalance() + "");
                    allow = data.getAllow();
                    if (data.getUserType() == 1) {
                        mydianpu.setVisibility(View.GONE);
                    } else {
                        mydianpu.setVisibility(View.VISIBLE);
                    }
                    if (!userUrl.startsWith("http://") && !userUrl.startsWith("https://")) {
                        userUrl = URLS.HTTP + userUrl;
                    }
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(URLS.getUser_id() + "", data.getNickname() ,Uri.parse(userUrl)));

                }

            }

            @Override
            public void onFailure(Exception e) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
            }
        });
    }

    private void initView(View view) {
        mRefresh = view.findViewById(R.id.sm_refresh);
        message_num = view.findViewById(R.id.message_num);
        mypager_touxiang = (CircleImageView) view.findViewById(R.id.mypager_touxiang);//头像
        mypager_name = (TextView) view.findViewById(R.id.mypager_name);//姓名
        //姓名
        mypage_version = (TextView) view.findViewById(R.id.mypage_version);//版本号
        mypage_version.setText(getLocalVersion(getActivity()));
        mypager_phone = (TextView) view.findViewById(R.id.mypager_phone);//手机号
        mypager_money = (TextView) view.findViewById(R.id.mypager_money);//钱包余额
        mLlMoney = (LinearLayout) view.findViewById(R.id.mypager_ll_money);//我的钱包(linearlayout点击事件)
        mypager_jifen = (TextView) view.findViewById(R.id.mypager_jifen);//积分
        mDingdan = (RelativeLayout) view.findViewById(R.id.mypager_dingdan);//我的订单
        my_jifen = (LinearLayout) view.findViewById(R.id.my_ll_jifen);//我的积分只有个人版有
        mInfo = (RelativeLayout) view.findViewById(R.id.mypagers_info);//个人信息
        mKefu = (RelativeLayout) view.findViewById(R.id.mypager_kefu);//联系客服
        mydongtai = (RelativeLayout) view.findViewById(R.id.mydongtai);//我的动态
        zaixiankefu = (RelativeLayout) view.findViewById(R.id.zaixiankefu);//在线私聊，跳转webview。
        mydianpu = (RelativeLayout) view.findViewById(R.id.mypager_mydianpu);//我的店铺
        dianpu = (RelativeLayout) view.findViewById(R.id.dianpu);//我的店铺

        mInfo.setOnClickListener(this);
        mDingdan.setOnClickListener(this);
        dianpu.setOnClickListener(this);
        mydongtai.setOnClickListener(this);
        mydianpu.setOnClickListener(this);
        mKefu.setOnClickListener(this);
        zaixiankefu.setOnClickListener(this);
        mLlMoney.setOnClickListener(this);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                germyneirong();

            }
        });

    }
    /* 获取本地软件版本号​
     */
    public static String getLocalVersion(Context ctx) {
        int localVersion = 0;
        String versionName = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            versionName = packageInfo.versionName;
            Log.d("TAG", "当前版本号：" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName+":"+localVersion;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            //个人信息点击事件
            case R.id.mypagers_info:
                intent.setClass(getActivity(), MyInfoActivity.class);
                startActivityForResult(intent, 10);
                break;
            //钱包点击事件
            case R.id.mypager_ll_money:
                intent.setClass(getActivity(), JinEActivity.class);
                intent.putExtra("shnegyu", mypager_money.getText().toString().trim());
                startActivityForResult(intent, 11);
                break;
            //积分点击事件
            case R.id.my_ll_jifen:
                Toast.makeText(getActivity(), "积分", Toast.LENGTH_SHORT).show();
                break;
            //我的订单点击事件
            case R.id.mypager_dingdan:
                intent.setClass(getActivity(), MyDingDansActivity.class);
                startActivity(intent);
                break;
            case R.id.mydongtai:
                //根据类型来判断跳转的activity
                if (type == 1) {
                    intent.setClass(getActivity(), MyDongTaiGRActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(getActivity(), MyDongTaiQYActivity.class);
                    startActivity(intent);
                }
                break;
            //商品店铺点击事件
            case R.id.mypager_mydianpu:
                if (allow == 0) {
                    showPopWindow();
                } else {
                    intent.setClass(getActivity(), MyDianPuActivity.class);
                    startActivity(intent);
                }
                break;
            //客服电话点击事件
            case R.id.mypager_kefu:
                showKefuDialog();
                break;
            //在线私聊点击事件
            case R.id.zaixiankefu:
                intent.setClass(getActivity(), Subconversationlist.class);
                startActivity(intent);
                break;
            //服务店铺
            case R.id.dianpu:
                intent.setClass(getActivity(), FuWuShopActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void showKefuDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.mypager_kefu_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        TextView tv_quxiao = (TextView) view.findViewById(R.id.kefu_dialog_quxiao);
        TextView tv_hujiao = (TextView) view.findViewById(R.id.kefu_dialog_hujiao);
        final TextView tv_dianhua = (TextView) view.findViewById(R.id.kefu_dialog_dianhua);

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
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15923400098"));
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_dianhua.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        keFuDialog = builder.create();
        keFuDialog.setView(view, 0, 0, 0, 0);
        keFuDialog.show();
    }


    private void showPopWindow() {

        View inflate = View.inflate(getActivity(), R.layout.pop_shop, null);
        TextView mCancel = inflate.findViewById(R.id.cancel);
        Button mAi_pay = inflate.findViewById(R.id.btn_alipay);
        Button mWx_PAY = inflate.findViewById(R.id.btn_wxpay);
        mPopupWindow = new MyPopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
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
                        Intent intent = new Intent(getActivity(), AilPayActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    } else if (payTYpe == 3) {
                        Intent intent = new Intent(getActivity(), WeiXinZFActivity.class);
                        intent.putExtra("pay", response.getData().getPayResult().getRequestData());
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(getActivity(), "支付失败哦", Toast.LENGTH_SHORT).show();
                    mPopupWindow.dismiss();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getActivity(), "支付失败哦", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 10:
                    Log.e("TAG", "RESULT_OK");
                    germyneirong();
                    break;
                case 11:
                    Log.e("TAG", "RESULT_OK");
                    germyneirong();
                    break;
            }

        }
    }

}
