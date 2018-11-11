package com.zx.zhuangxiu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.PermissionUtils;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.fragment.BusinessPageFragment;
import com.zx.zhuangxiu.fragment.FoundPageFragment;
import com.zx.zhuangxiu.fragment.HomePageFragment;
import com.zx.zhuangxiu.fragment.MyPageFragment;
import com.zx.zhuangxiu.model.IMBean;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int ITEM_HOME = 1;   //首页精选
    public static final int ITEM_FOUND = 2; // 发现
    public static final int ITEM_BUSINESS = 3; // 商机
    public static final int ITEM_My = 4; // 我的-个人中心

    private HomePageFragment mHomePageFragment;
    private FoundPageFragment mFoundPageFragment;
    private BusinessPageFragment mBusinessPageFragment;
    private MyPageFragment mMyPageFragment;

    private RadioGroup mRadioGroup;
    private RadioButton mRbHome, mRbFound, mRbBusiness, mRbMy;

    private int mCurrentItem;
    private long mExitToastTime;
    private Fragment frag = new HomePageFragment();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what>0&&msg.what<=99){
                message_num.setVisibility(View.VISIBLE);
                message_num.setText(msg.what+"");
            }else if (msg.what>99){
                message_num.setVisibility(View.VISIBLE);
                message_num.setText(msg.what+"");
            }else if (msg.what==0){
                message_num.setVisibility(View.GONE);
            }

        }
    };
    private TextView message_num;
    //    private ActionBar mActionBar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showHomeFragment(savedInstanceState);

        setContentView(R.layout.activity_home);
        initViews();
        // 显示首页
        switchItem(ITEM_HOME);
        String ss=URLS.HTTP+"/api/IM/IMregister?userId="+URLS.getUser_id()+"&nickname="+"13"+"&headImg="+"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png";
        OkHttpUtils.get(ss, new OkHttpUtils.ResultCallback<IMBean>() {
            @Override
            public void onSuccess(IMBean response) {

                RongIM.connect(response.getData(), new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                    }
                    @Override
                    public void onSuccess(String userId) {

                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });

            }

            @Override
            public void onFailure(Exception e) {

            }
        });


        PermissionUtils.permission(Manifest.permission.CAMERA).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {

            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {

            }
        }).rationale(new PermissionUtils.OnRationaleListener() {
            @Override
            public void rationale(ShouldRequest shouldRequest) {

            }
        }).request();

        //TODO

        //融云消息过多就变色  zy
        RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new RongIM.OnReceiveUnreadCountChangedListener() {
            @Override
            public void onMessageIncreased(int i) {
                Message obtain = Message.obtain();
                obtain.what=i;
                handler.sendMessage(obtain);
                Log.e("TAG",i+"");

            }
        }, Conversation.ConversationType.PRIVATE);


    }


    private void initViews() {
        mRbHome = (RadioButton) findViewById(R.id.tab_rb_home);
        mRbBusiness = (RadioButton) findViewById(R.id.tab_rb_business);
        mRbFound = (RadioButton) findViewById(R.id.tab_rb_found);
        mRbMy = (RadioButton) findViewById(R.id.tab_rb_my);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        mRbHome.setOnClickListener(this);
        mRbBusiness.setOnClickListener(this);
        mRbFound.setOnClickListener(this);
        mRbMy.setOnClickListener(this);
        message_num = findViewById(R.id.message_num);
    }

    private void switchItem(int itemId) {
        switch (itemId) {
            case ITEM_My:
                mCurrentItem = ITEM_My;
                if (mMyPageFragment == null) {
                    mMyPageFragment = new MyPageFragment();
                }
                switchContent(frag, mMyPageFragment, "person");
                frag = mMyPageFragment;
                break;
            case ITEM_HOME:
                mCurrentItem = ITEM_HOME;
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                }
                switchContent(frag, mHomePageFragment, "home");
                frag = mHomePageFragment;
                break;
            case ITEM_BUSINESS:
                mCurrentItem = ITEM_BUSINESS;
                if (mBusinessPageFragment == null) {
                    mBusinessPageFragment = new BusinessPageFragment();
                }
                switchContent(frag, mBusinessPageFragment, "business");
                frag = mBusinessPageFragment;
                break;
            case ITEM_FOUND:
                mCurrentItem = ITEM_FOUND;
                if (mFoundPageFragment == null) {
                    mFoundPageFragment = new FoundPageFragment();
                }
                switchContent(frag, mFoundPageFragment, "found");
                frag = mFoundPageFragment;
                break;
        }

    }

    public void switchContent(Fragment from, Fragment to, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (to != null && from != null){
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.rb_container, to, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab_rb_home:
                switchItem(ITEM_HOME);
//                mActionBar.setTitle("精选");
                break;
            case R.id.tab_rb_business:
                switchItem(ITEM_BUSINESS);
//                mActionBar.setTitle("分类");
                break;
            case R.id.tab_rb_found:
                switchItem(ITEM_FOUND);
//                mRadioGroup.clearCheck();
//                mActionBar.setTitle("VIP");
                break;
            case R.id.tab_rb_my:
                switchItem(ITEM_My);
//                mActionBar.setTitle("我的");
                break;
            default:
                break;
        }
    }

    private void showHomeFragment(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            mHomePageFragment = (HomePageFragment) fragmentManager.findFragmentByTag("home");
            mBusinessPageFragment = (BusinessPageFragment) fragmentManager.findFragmentByTag("business");
            mFoundPageFragment = (FoundPageFragment) fragmentManager.findFragmentByTag("found");
            mMyPageFragment = (MyPageFragment) fragmentManager.findFragmentByTag("person");
            fragmentManager.beginTransaction()
                    .show(mHomePageFragment)
                    .hide(mBusinessPageFragment)
                    .hide(mFoundPageFragment)
                    .hide(mMyPageFragment)
                    .commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //点击两次退出
    public void exit() {
        if ((System.currentTimeMillis() - mExitToastTime) > 2000) {
            Toast.makeText(HomeActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitToastTime = System.currentTimeMillis();
        } else {
            Intent MyIntent = new Intent(Intent.ACTION_MAIN);
            MyIntent.addCategory(Intent.CATEGORY_HOME);
            startActivity(MyIntent);
            finish();
            System.exit(0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
  /*      *//*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*//*
        Fragment person = getSupportFragmentManager().findFragmentByTag("person");
        *//*然后在碎片中调用重写的onActivityResult方法*//*
        person.onActivityResult(requestCode, resultCode, data);*/
    }
}
