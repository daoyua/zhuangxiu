package com.zx.zhuangxiu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.UpdateUtils;
import com.zx.zhuangxiu.fragment.BusinessPageFragment;
import com.zx.zhuangxiu.fragment.FoundPageFragment;
import com.zx.zhuangxiu.fragment.HomePageFragment;
import com.zx.zhuangxiu.fragment.MyPageFragment;
import com.zx.zhuangxiu.model.AddressService;
import com.zx.zhuangxiu.model.IMBean;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private TextView message_num;
    //    private ActionBar mActionBar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showHomeFragment(savedInstanceState);

        setContentView(R.layout.activity_home);
        //自动更新
        new UpdateUtils().UpdataApk(this);
        initViews();
        // 显示首页
        switchItem(ITEM_HOME);
        String ss = URLS.HTTP + "/api/IM/IMregister?userId=" + URLS.getUser_id() + "&nickname=" + "13" + "&headImg=" + "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png";
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

//        }


        //TODO

        //融云消息过多就变色  zy
        RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new RongIM.OnReceiveUnreadCountChangedListener() {
            @Override
            public void onMessageIncreased(int i) {
                Message obtain = Message.obtain();
                obtain.what = i;
                handler.sendMessage(obtain);
                Log.e("TAG", i + "");

            }
        }, Conversation.ConversationType.PRIVATE);
//        initPermission();
        checkPermission();

    }

    private boolean mShowRequestPermission = true;

    private void initPermission() {
//    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
//    List<String> mPermissionList = new ArrayList<>();
//    for (int i = 0; i < permissions.length; i++) {
//        if (ContextCompat.checkSelfPermission(HomeActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
//            mPermissionList.add(permissions[i]);
//        }
//    }
//    if (mPermissionList.isEmpty()) {// 全部允许
//        mShowRequestPermission = true;
//        updateAddress();
//    } else {//存在未允许的权限
//        String[] permissionsArr = mPermissionList.toArray(new String[mPermissionList.size()]);
//        ActivityCompat.requestPermissions(HomeActivity.this, permissionsArr, 101);
//    }

//        checkPermission();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        if (ContextCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CAMERA}, 2);
        } else {
            updateAddress();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                //判断是否勾选禁止后不再询问
                boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissions[i]);
                if (showRequestPermission) {
                    checkPermission();
                    return;
                } else { // false 被禁止了，不在访问
                    mShowRequestPermission = false;//已经禁止了
                }
            }
        }
        checkPermission();
    }



    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    private void updateAddress() {

        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    Toast.makeText(HomeActivity.this, aMapLocation.getLongitude() + ":" + aMapLocation.getLatitude(), Toast.LENGTH_LONG).show();
                    updateAddressServer(aMapLocation.getLongitude() + "", aMapLocation.getLatitude() + "");

                    if (mlocationClient != null) {
                        mlocationClient.stopLocation();
                        mlocationClient.onDestroy();
                    }
                    mlocationClient = null;
                }
            });
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }


    }

    private void updateAddressServer(final String lon, final String lat) {
        String ss = URLS.updateDynamicAddress(lon, lat);
        Constants.lat = lat;
        Constants.lon = lon;

        OkHttpUtils.get(ss, new OkHttpUtils.ResultCallback<AddressService>() {
            @Override
            public void onSuccess(AddressService response) {
//                Toast.makeText(HomeActivity.this,lon+":"+lat,Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeActivity.this, response.getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(HomeActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();
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
        if (to != null && from != null) {
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
                mCurrentItem = ITEM_FOUND;
//                startActivity(new Intent(HomeActivity.this, TestActivity.class));

//                BDAutoUpdateSDK.uiUpdateAction(this, new UICheckUpdateCallback() {
//                    @Override
//                    public void onNoUpdateFound() {
//                        ToastUtil.showLong(HomeActivity.this,"没有更新");
//                    }
//
//                    @Override
//                    public void onCheckComplete() {
//                        ToastUtil.showLong(HomeActivity.this,"检查完毕");
//                    }
//                });
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
        fragmentManager.beginTransaction().addToBackStack(null);
//        fragmentTransaction.addToBackStack(null);
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
