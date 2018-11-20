package com.zx.zhuangxiu.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.DaShiQuanActivity;
import com.zx.zhuangxiu.activity.FoundFu2WuActivity;
import com.zx.zhuangxiu.activity.FoundFu3WuActivity;
import com.zx.zhuangxiu.activity.FoundFuWuActivity;
import com.zx.zhuangxiu.activity.FoundGoodsActivity;
import com.zx.zhuangxiu.activity.FoundWorkActivity;
import com.zx.zhuangxiu.activity.FoundWorkerActivity;
import com.zx.zhuangxiu.activity.HomeYuShiActivity;
import com.zx.zhuangxiu.activity.JiamemghezuoActivity;
import com.zx.zhuangxiu.activity.MyDianPuActivity;
import com.zx.zhuangxiu.activity.SouSuoActivity;
import com.zx.zhuangxiu.activity.XiaoXiNoticeActivity;
import com.zx.zhuangxiu.adapter.HorizontalListViewAdapter;
import com.zx.zhuangxiu.adapter.JinPaiListViewAdapter;
import com.zx.zhuangxiu.adapter.SiRenDzListviewAdapter;
import com.zx.zhuangxiu.adapter.ZhijiejinruAdapter;
import com.zx.zhuangxiu.model.BannerBean;
import com.zx.zhuangxiu.model.BestService;
import com.zx.zhuangxiu.model.DaShiQuanLieBiaoOne;
import com.zx.zhuangxiu.model.DaShiQuanLieBiaoTwo;
import com.zx.zhuangxiu.model.FindService;
import com.zx.zhuangxiu.model.HuoquxiaoxiBean;
import com.zx.zhuangxiu.model.ShopCart;
import com.zx.zhuangxiu.model.SpTuiJianTwo;
import com.zx.zhuangxiu.model.ZhijiejinruBean;
import com.zx.zhuangxiu.myinterface.MarqueeTextViewClickListener;
import com.zx.zhuangxiu.utils.ToastUtil;
import com.zx.zhuangxiu.view.HorizontalListView;
import com.zx.zhuangxiu.view.MarqueeTextView;
import com.zx.zhuangxiu.view.MylistView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePageFragment extends Fragment implements View.OnClickListener, AMapLocationListener {
    //声明AMapLocationClient类对象，定位发起端
    private AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象，定位参数
    public AMapLocationClientOption mLocationOption = null;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    private HorizontalListView hListView;
    private HorizontalListViewAdapter hListViewAdapter;
    private Context mContext;
    private ShopCart mShopCart;
    private List<ShopCart> mLists;

    private LinearLayout home_gongren, home_chanpin, home_work, home_service;

    //金牌服务ListView
    private MylistView home_jinpai_listview;
    private JinPaiListViewAdapter jinPaiListViewAdapter;
    private List<BestService.DataBean> syJInPaiList = new ArrayList<>();

    //私人订制ListView
    private MylistView home_dingzhi_listview;
    private List<BestService.DataBean> syFuWuTwoList = new ArrayList<>();
    private SiRenDzListviewAdapter siRenDzListviewAdapter;

    //消息
    private ImageView home_xiaoxi, home_yushi1, home_yushi2, home_yushi3, home_yushi4, home_yushi5;

    private TextView home_tv;  //搜索查询
    private RelativeLayout home_dashiquan;  //大时圈头条
    //滚动通知
    private MarqueeTextView marqueeTextView;
    // 上下滚动栏
    private String[] textArrays;

    private TextView weizhi;

    //商品推荐
    private List<SpTuiJianTwo.DataBean> spTuiJianTwoList = new ArrayList<>();

    //大时圈头条
    private TextView home_tv_toutiao1, home_tv_toutiao2, home_tv_toutiao3;
    private List<DaShiQuanLieBiaoTwo> daShiQuanLieBiaoTwoList = new ArrayList<>();
    private Banner mBanner;
    private List<String> images = new ArrayList<>();
    private List<HuoquxiaoxiBean.DataBean> xiaoxilist = new ArrayList<>();
    private MylistView zhijiejinrulistview;

    private List<ZhijiejinruBean.DataBean> zhijijinrulist = new ArrayList<>();
    private ZhijiejinruAdapter zhijiejinruadapter;
    private int userID;
    private ImageView hongdianimage;
    private SmartRefreshLayout mRefresh;
    int anInt = 0;
    int page = 0;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (page == anInt) {
                mRefresh.finishRefresh();
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homepage, container, false);

        userID = getActivity().getIntent().getIntExtra("userid", -1);
        if (userID != -1) {
        }
        initView(view);
        location();
        initData();
        return view;
    }

    private void initView(View view) {
        mRefresh = view.findViewById(R.id.refresh);
        mContext = getActivity();
        mBanner = view.findViewById(R.id.banner);
        weizhi = (TextView) view.findViewById(R.id.tweizhi);
        home_service = (LinearLayout) view.findViewById(R.id.home_service);  //找服务
        home_work = (LinearLayout) view.findViewById(R.id.home_work);  //找工作
        home_gongren = (LinearLayout) view.findViewById(R.id.home_gongren);  //找工人
        home_chanpin = (LinearLayout) view.findViewById(R.id.home_chanpin);  //找产品
        home_xiaoxi = (ImageView) view.findViewById(R.id.home_xiaoxi);  //消息
        home_yushi1 = (ImageView) view.findViewById(R.id.home_yushi1);  //石材玉石
        home_yushi2 = (ImageView) view.findViewById(R.id.home_yushi2);  //石材玉石
        home_yushi3 = (ImageView) view.findViewById(R.id.home_yushi3);  //石材玉石
        home_yushi4 = (ImageView) view.findViewById(R.id.home_yushi4);  //石材玉石
        home_yushi5 = (ImageView) view.findViewById(R.id.home_yushi5);  //石材玉石
        home_tv = (TextView) view.findViewById(R.id.home_tv);
        home_dashiquan = (RelativeLayout) view.findViewById(R.id.home_dashiquan);
        marqueeTextView = (MarqueeTextView) view.findViewById(R.id.scroltextview);//自定义滚动通知控件
        hongdianimage = view.findViewById(R.id.home_hongdian);

        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getDaShiQuanInfo();
                getTuiJianInfo(); // 推荐
                getJinPaiInfo();  //获取金牌服务的信息
                getSiRenDzInfo();  //获取私人订制
                getImage();
                getData();
                getxinxiaoxi();//是否有最新消息

            }
        });


        home_dashiquan.setOnClickListener(this);
        home_xiaoxi.setOnClickListener(this);
        home_gongren.setOnClickListener(this);
        home_chanpin.setOnClickListener(this);
        home_yushi1.setOnClickListener(this);
        home_yushi2.setOnClickListener(this);
        home_yushi3.setOnClickListener(this);
        home_yushi4.setOnClickListener(this);
        home_yushi5.setOnClickListener(this);
        home_work.setOnClickListener(this);
        home_service.setOnClickListener(this);
        home_tv.setOnClickListener(this);

        hListView = (HorizontalListView) view.findViewById(R.id.horizon_listview);  //商品推荐
        hListViewAdapter = new HorizontalListViewAdapter(mContext, spTuiJianTwoList);
        hListView.setAdapter(hListViewAdapter);
        home_jinpai_listview = (MylistView) view.findViewById(R.id.home_jinpai_listview);  //金牌服务
        home_dingzhi_listview = (MylistView) view.findViewById(R.id.home_dingzhi_listview);  //私人订制
        home_jinpai_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FoundFu2WuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "金牌服务");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        home_dingzhi_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FoundFu3WuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "私人定制");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //大时圈头条
        home_tv_toutiao1 = (TextView) view.findViewById(R.id.home_tv_toutiao1);
        home_tv_toutiao2 = (TextView) view.findViewById(R.id.home_tv_toutiao2);
        home_tv_toutiao3 = (TextView) view.findViewById(R.id.home_tv_toutiao3);

        getDaShiQuanInfo();
        getTuiJianInfo(); // 推荐
        getJinPaiInfo();  //获取金牌服务的信息
        getSiRenDzInfo();  //获取私人订制
        getImage();
        getData();
        getxinxiaoxi();//是否有最新消息

    }

    /**
     * 获取是否有新消息
     */
    private void getxinxiaoxi() {
        anInt++;
        String url = URLS.getshouyexiaoxi();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<HuoquxiaoxiBean>() {
            @Override
            public void onSuccess(HuoquxiaoxiBean response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {
                    xiaoxilist.add(response.getData());
                    for (int i = 0; i < xiaoxilist.size(); i++) {
                        int num = xiaoxilist.get(i).getNum();
                        if (num > 1) {
                            hongdianimage.setVisibility(View.VISIBLE);
                            ToastUtil.showShort(getActivity(), "您收到最新消息，请注意查看！");
                        } else {
                            hongdianimage.setVisibility(View.GONE);
                        }
                    }


                }

            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }

    /**
     * 轮播图
     */
    private void getImage() {
        anInt++;
        OkHttpUtils.get(URLS.BANNER, new OkHttpUtils.ResultCallback<BannerBean>() {
            @Override
            public void onSuccess(BannerBean response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {

                    images.clear();
                    final List<BannerBean.DataBean> data = response.getData();
                    for (BannerBean.DataBean datum : data) {
                        images.add(URLS.HTTP + datum.getWi_address());
                    }
                    mBanner.setImages(images);
                    mBanner.start();
                    mBanner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {

                            if (data.get(position).getUserId() == -1) {
                                Intent intent = new Intent(getActivity(), JiamemghezuoActivity.class);
                                startActivity(intent);
                                /*HomeActivity activity = (HomeActivity) getActivity();
                                activity.loadShop();*/
                            } else {
                                Intent intent = new Intent(getActivity(), MyDianPuActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("sellerId", data.get(position).getUserId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }

    private void initData() {
   /*     images.add("http://47.93.215.205:9095/upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png");
        images.add("http://47.93.215.205:9095/upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png");
        images.add("http://47.93.215.205:9095/upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png");
        images.add("http://47.93.215.205:9095/upload/20180828/a31651ce-f068-4511-a9ed-f78f6372b5d7.png");*/


        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合

        //设置banner动画效果
//        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
    }

    //获取大时圈头条信息
    private void getDaShiQuanInfo() {
        anInt++;
        String url = URLS.daShiQuanShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DaShiQuanLieBiaoOne>() {
            @Override
            public void onSuccess(DaShiQuanLieBiaoOne response) {
                handler.sendEmptyMessage(page++);

                if (response.getId() == 0) {
                    daShiQuanLieBiaoTwoList.clear();
                    daShiQuanLieBiaoTwoList = response.getData();

                    if (daShiQuanLieBiaoTwoList != null) {
                        int a = daShiQuanLieBiaoTwoList.size();
                        textArrays = new String[a];
                        for (int i = 0; i < a; i++) {
                            textArrays[i] = daShiQuanLieBiaoTwoList.get(i).getTitle();
                        }

                        ggDate();
                    }

                }

            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }

    //获取金牌服务的信息
    private void getJinPaiInfo() {
        anInt++;
        String url = URLS.syJinPaiFuWuShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BestService>() {
            @Override
            public void onSuccess(BestService response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {
                    syJInPaiList.clear();
                    syJInPaiList.addAll(response.getData());
                    jinPaiListViewAdapter = new JinPaiListViewAdapter(mContext, syJInPaiList);
                    home_jinpai_listview.setAdapter(jinPaiListViewAdapter);
                }
            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }


    private void getData() {
        anInt++;
        OkHttpUtils.get("http://47.93.215.205:9095/api/server/serverList", new OkHttpUtils.ResultCallback<FindService>() {
            @Override
            public void onSuccess(FindService response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {
                    Log.e("TAG", response.getMsg().toString());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", e.getMessage());
                handler.sendEmptyMessage(page++);
            }
        });

    }

    //获取商品推荐信息
    private void getTuiJianInfo() {
        anInt++;
        String urlPath = URLS.shangPinTuiJianShow();
        OkHttpUtils.get(urlPath, new OkHttpUtils.ResultCallback<SpTuiJianTwo>() {
            @Override
            public void onSuccess(SpTuiJianTwo response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {
                    spTuiJianTwoList.clear();
                    spTuiJianTwoList.addAll(response.getData());
                    hListViewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }

    //获取私人订制
    private void getSiRenDzInfo() {
        anInt++;
        String url = URLS.sysrdzFuWuShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BestService>() {
            @Override
            public void onSuccess(BestService response) {
                handler.sendEmptyMessage(page++);
                if (response.getResult() == 1) {
                    syFuWuTwoList.clear();
                    syFuWuTwoList.addAll(response.getData());
                    siRenDzListviewAdapter = new SiRenDzListviewAdapter(mContext, syFuWuTwoList);
                    home_dingzhi_listview.setAdapter(siRenDzListviewAdapter);
                }
            }

            @Override
            public void onFailure(Exception e) {
                handler.sendEmptyMessage(page++);
            }
        });
    }


    //广告通知滚动
    public void ggDate() {
        // 滚动消息栏的显示内容
        marqueeTextView.setTextArraysAndClickListener(textArrays, new MarqueeTextViewClickListener() {
            @Override
            public void onClick(View view) {
//                //点击跳转通知详情
//                Toast.makeText(context,"点击了",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.home_service://找服务
                intent.setClass(getActivity(), FoundFuWuActivity.class);
                Bundle bundle0 = new Bundle();
                bundle0.putInt("type", 1);
                bundle0.putString("title", "找服务");
                intent.putExtras(bundle0);
                startActivity(intent);
                break;
            case R.id.home_work://找工作
                intent.setClass(getActivity(), FoundWorkActivity.class);
                startActivity(intent);
                break;
            case R.id.home_gongren://找工人
                intent.setClass(getActivity(), FoundWorkerActivity.class);
                startActivity(intent);
                break;
            case R.id.home_chanpin://找产品
                intent.setClass(getActivity(), FoundGoodsActivity.class);
                startActivity(intent);
                break;
            case R.id.home_xiaoxi://消息通知
                intent.setClass(getActivity(), XiaoXiNoticeActivity.class);
                startActivityForResult(intent, 0x1000);
                break;
            case R.id.home_yushi1:
                intent.setClass(getActivity(), HomeYuShiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "石材玉石");
                bundle.putInt("weizhi", 39);
                bundle.putInt("lunboid", 39);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.home_yushi2:
                intent.setClass(getActivity(), HomeYuShiActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("title", "清洁地坪");
                bundle1.putInt("weizhi", 11);
                bundle1.putInt("lunboid", 11);
                intent.putExtras(bundle1);
                startActivity(intent);
                break;
            case R.id.home_yushi3:
                intent.setClass(getActivity(), HomeYuShiActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("title", "酒店用品");
                bundle2.putInt("weizhi", 40);
                bundle2.putInt("lunboid", 40);
                intent.putExtras(bundle2);
                startActivity(intent);
                break;
            case R.id.home_yushi4:
                intent.setClass(getActivity(), HomeYuShiActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("title", "二手市场");
                bundle3.putInt("weizhi", 14);
                bundle3.putInt("lunboid", 14);
                intent.putExtras(bundle3);
                startActivity(intent);
                break;
            case R.id.home_yushi5:
                intent.setClass(getActivity(), HomeYuShiActivity.class);
                Bundle bundle4 = new Bundle();
                bundle4.putString("title", "五金建材");
                bundle4.putInt("weizhi", 13);
                bundle4.putInt("lunboid", 13);
                intent.putExtras(bundle4);
                startActivity(intent);
                break;
            case R.id.home_tv:  //搜索
                intent.setClass(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
            case R.id.home_dashiquan:  //大时圈头条
                intent.setClass(getActivity(), DaShiQuanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void location() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码

                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //获取定位信息
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Log.e("--->", buffer.toString());
                    weizhi.setText(aMapLocation.getCity() + "");
                    URLS.setLocation(aMapLocation.getCity());
//                    init(aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet() + "");
                    isFirstLoc = false;
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                switch (aMapLocation.getErrorCode()) {
                    case 1:
                        Log.e("--->定位失败", "重要参数为空");
                        break;
                    case 2:
                        Log.e("--->定位失败", "WIFI信息不足");
                        break;
                    case 3:
                        Log.e("--->定位失败", "请求参数获取出现异常");
                        break;
                    case 4:
                        Log.e("--->定位失败", "网络连接异常");
                        break;
                    case 5:
                        Log.e("--->定位失败", "解析XML出错");
                        break;
                    case 6:
                        Log.e("--->定位失败", "定位结果错误");
                        break;
                    case 7:
                        Log.e("--->定位失败", "KEY错误");
                        break;
                    case 8:
                        Log.e("--->定位失败", "其他错误");
                        break;
                    case 9:
                        Log.e("--->定位失败", "初始化异常");
                        break;
                    case 10:
                        Log.e("--->定位失败", "定位服务启动失败");
                        break;
                    case 11:
                        Log.e("--->定位失败", "错误的基站信息，请检查是否插入SIM卡");
                        break;
                    case 12:
                        Log.e("--->定位失败", "您未开启定位权限");
                        break;
                }
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。

        marqueeTextView.releaseResources();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x1000:
                getxinxiaoxi();
                break;
        }
    }

    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

        }

    }
}


