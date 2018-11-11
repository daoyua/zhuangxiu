package com.zx.zhuangxiu.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.AddAdapter;
import com.zx.zhuangxiu.adapter.DetailsListViewAdapter;
import com.zx.zhuangxiu.adapter.ImageAdapter;
import com.zx.zhuangxiu.fragment.GouMaiFragment;
import com.zx.zhuangxiu.fragment.TuWenFragment;
import com.zx.zhuangxiu.model.AddressOne;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.CommentsBean;
import com.zx.zhuangxiu.model.DetailsFive;
import com.zx.zhuangxiu.model.DetailsTwo;
import com.zx.zhuangxiu.model.DingdanBean;
import com.zx.zhuangxiu.model.JisuanBean;
import com.zx.zhuangxiu.model.MydexinxiBean;
import com.zx.zhuangxiu.model.PingLunLieBiaoTwo;
import com.zx.zhuangxiu.model.ShangpinguigeBean;
import com.zx.zhuangxiu.model.ShangpinguigesBean;
import com.zx.zhuangxiu.utils.ToastUtil;
import com.zx.zhuangxiu.view.MylistView;
import com.zx.zhuangxiu.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.FormBody;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager detailsinfo_one_viewpager;
    //存放小圆点的布局
    private ViewGroup detailsinfo_one_viewGroup;
    private ArrayList<View> pageViews;
    private ImageView imageView;
    private TextView detailsinfo_back;
    private ImageView[] imageViews;
    private RadioButton detailsinfo_one_rb1, detailsinfo_one_rb2;
    private FragmentManager fm;
    private TuWenFragment tuWenFragment;
    private GouMaiFragment gouMaiFragment;

    private TextView detailsinfo_one_datitle, detailsinfo_one_xiaotitle, detailsinfo_one_money, detailsinfo_one_yishou, detailsinfo_one_yscm, detailsinfo_one_addShop;
    private MylistView detailsinfo_listview;
    private ImageAdapter detailsListViewAdapter;
    private List<PingLunLieBiaoTwo> pingLunLieBiaoTwos = new ArrayList<>();

    private LinearLayout detailsinfo_one_gouwuche;  //购物车

    private List<DetailsTwo> detailsTwoList = new ArrayList<>();
    private List<DetailsFive.DataBean> dataBeanList = new ArrayList<>();
    private int pkId;

    private TextView detailsinfo_one_jiagouwuche;
    private String dingDanHao = "";

    private int pikid = 203;

    //底部弹出框
    private RoundImageView gouwuche_img;
    private TextView gouwuche_money, gouwuche_kucun, iv_jian, tv_shuliang, iv_jia, submit;
    private int count = 1;

    private LinearLayout rl_guige;  //规格
    private Banner banner;

    private List<ShangpinguigeBean.DataBean> guigelist = new ArrayList<>();

    private int shangpinid;

    private List<DingdanBean.DataBean> dingdanlist = new ArrayList<>();
    private int paylogId;
    private int pricejiage;
    private List<ShangpinguigesBean.DataBean.ItemlistBean.DatalistBean> guigelists = new ArrayList<>();
    private double jiage;
    private String imageurls;
    private int spOne;
    private int spTwo;
    private TextView detailsdianpu;
    private int sellerId;
    private int shangpin;
    private List<String> pinglunlist = new ArrayList<>();
    private List<CommentsBean.DataBean> pinglun = new ArrayList<>();
    private LinearLayout guige;
    private TextView tv_guige;
    private ImageView close;
    private TextView add;
    private GridView gridView;
    private AddAdapter addAdapter;
    private List<ShangpinguigeBean.DataBean.ItemlistBean> list;
    private MylistView _listview;
    private DetailsListViewAdapter detailsListAdapter;
    private Context content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle = getIntent().getExtras();
        pkId = bundle.getInt("pkId");
        shangpin = bundle.getInt("shangpin");
        initView();
        //获取到商品详情消息
        getXqxiaoxi();
        //商品品论列表
        getPingLunInfo(0, pkId);
        getmorenaddress();
        content=this;
    }

    private void initView() {
        rl_guige = (LinearLayout) findViewById(R.id.guige);
        rl_guige.setOnClickListener(this);

        detailsinfo_back = (TextView) findViewById(R.id.detailsinfo_back);
        detailsinfo_back.setOnClickListener(this);

        detailsinfo_one_datitle = (TextView) findViewById(R.id.detailsinfo_one_datitle);
        detailsinfo_one_xiaotitle = (TextView) findViewById(R.id.detailsinfo_one_xiaotitle);
        detailsinfo_one_money = (TextView) findViewById(R.id.detailsinfo_one_money);
        detailsinfo_one_yishou = (TextView) findViewById(R.id.detailsinfo_one_yishou);
        detailsinfo_one_yscm = (TextView) findViewById(R.id.detailsinfo_one_yscm);
        detailsinfo_listview = (MylistView) findViewById(R.id.detailsinfo_listview);
        _listview = (MylistView) findViewById(R.id._listview);
        banner = findViewById(R.id.details_bannner);

        detailsinfo_one_jiagouwuche = (TextView) findViewById(R.id.detailsinfo_one_jiagouwuche);  //直接购买
        detailsinfo_one_jiagouwuche.setOnClickListener(this);

        detailsinfo_one_gouwuche = (LinearLayout) findViewById(R.id.detailsinfo_one_gouwuche);
        detailsinfo_one_gouwuche.setOnClickListener(this);

        detailsinfo_one_addShop = (TextView) findViewById(R.id.detailsinfo_one_addShop);
        detailsinfo_one_addShop.setOnClickListener(this);

        detailsinfo_one_rb1 = (RadioButton) findViewById(R.id.detailsinfo_one_rb1);
        detailsinfo_one_rb2 = (RadioButton) findViewById(R.id.detailsinfo_one_rb2);
        detailsdianpu = findViewById(R.id.details_dianpu);

        detailsdianpu.setOnClickListener(this);
        detailsinfo_one_rb1.setOnClickListener(this);
        detailsinfo_one_rb2.setOnClickListener(this);
        fm = getSupportFragmentManager();
        findViewById(R.id.detailsinfo_one_shoucang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = URLS.mydata(sellerId);
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
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(sellerId+"", userName, Uri.parse(userUrl)));
                        RongIM.getInstance().setMessageAttachedUserInfo(true);
                        RongIM.getInstance().startPrivateChat(content, sellerId + "", "客服");
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });


            }
        });

        //跳转商家店铺
        detailsdianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, MyDianPuActivity.class);
                intent.putExtra("sellerId", sellerId);
                startActivity(intent);
            }
        });

        tv_guige = findViewById(R.id.tv_guige);
    }

    private void getPingLunInfo(int type, int id) {
        String url = URLS.pingLunShow(type, id);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<CommentsBean>() {
            @Override
            public void onSuccess(CommentsBean response) {
                if (response.getResult() == 1) {
                    if (response.getData() != null) {
                        pinglun = response.getData();
                        detailsListAdapter = new DetailsListViewAdapter(DetailsActivity.this, pinglun);
                        _listview.setAdapter(detailsListAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void jiesuan(final int pkId, int spOne, int spTwo, final int num) {
        OkHttpUtils.get(URLS.qujiesuan(URLS.getUser_id(), pkId, spOne, spTwo, num), new OkHttpUtils.ResultCallback<JisuanBean>() {
            @Override
            public void onSuccess(JisuanBean response) {
                if (response.getResult() == 1) {

                    Intent intent = new Intent(DetailsActivity.this, JieSuanActivity.class);
                    intent.putExtra("jiage", list.get(addAdapter.getSeclection()).getDatalist().get(0).getMoney());
                    intent.putExtra("image", list.get(addAdapter.getSeclection()).getDatalist().get(0).getMoney());
                    intent.putExtra("productId", pkId);
                    intent.putExtra("attributeId", response.getData().getAttributeId());
                    intent.putExtra("count", num);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    /**
     * 商品规格
     */
    private void getshangpinguige() {
        String url2 = URLS.shangpinguige(shangpinid);
        OkHttpUtils.get(url2, new OkHttpUtils.ResultCallback<ShangpinguigesBean>() {
            @Override
            public void onSuccess(ShangpinguigesBean response) {
                if (response.getResult() == 1) {
                    List<ShangpinguigesBean.DataBean> data = response.getData();
                    if (data != null && data.size() > 0) {
//                        detailsinfo_one_money.setText("￥" + data.get(0).getItemlist().get(0).getDatalist().get(0).getMoney());

                    }


                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    /**
     * 获取详情消息
     */
    private void getXqxiaoxi() {
        String url = URLS.detailsShow(pkId);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<DetailsFive>() {

            @Override
            public void onSuccess(DetailsFive response) {
                if (response.getResult() == 1) {
                    if (response.getData() != null) {
                        dataBeanList.add(response.getData());
                        for (int i = 0; i < dataBeanList.size(); i++) {
                            shangpinid = dataBeanList.get(i).getId();
                            sellerId = dataBeanList.get(i).getSellerId();
                            detailsinfo_one_datitle.setText(dataBeanList.get(i).getCdname());
                            detailsinfo_one_xiaotitle.setText(dataBeanList.get(i).getSimple());
                            detailsinfo_one_yishou.setText("已出售" + dataBeanList.get(i).getPin() + "件 | 库存" + dataBeanList.get(0).getSurplusCount() + "件");
                            detailsinfo_one_money.setText(dataBeanList.get(i).getPrice() + "");

                            List<String> imageurl = new ArrayList<>();
                            String im = dataBeanList.get(i).getBroadcastUrl();
                            if (im != null && im.length() > 0 && im.contains(",")) {
                                String[] imagh = im.split(",");
                                for (String img : imagh) {
                                    imageurl.add(URLS.HTTP + img);
                                }
                            }
                            String broadcastUrl = response.getData().getBroadcastUrl();

                            if (!TextUtils.isEmpty(broadcastUrl)) {
                                String[] split = broadcastUrl.split(",");
                                for (int i1 = 0; i1 < split.length; i1++) {
                                    pinglunlist.add(split[i1]);
                                }
                            }

                            detailsListViewAdapter = new ImageAdapter(DetailsActivity.this, pinglunlist);
                            detailsinfo_listview.setAdapter(detailsListViewAdapter);
                            //设置内置样式，共有六种可以点入方法内逐一体验使用。
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            //设置图片加载器，图片加载器在下方
                            banner.setImageLoader(new MyLoader());
                            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                            banner.setBannerAnimation(Transformer.Default);
                            //设置轮播间隔时间
                            banner.setDelayTime(3000);
                            //设置是否为自动轮播，默认是“是”。
                            banner.isAutoPlay(true);
                            //设置指示器的位置，小点点，左中右。
                            banner.setIndicatorGravity(BannerConfig.CENTER);
                                /*//以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                                .setOnBannerListener(this)
                                //必须最后调用的方法，启动轮播图。
                                .start();*/
                            banner.setImages(imageurl);
                            banner.start();

                        }
//                        getshangpinguige();
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    /**
     * 自定义图片加载器
     */
    public class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }


    /**
     * @param view
     */

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.detailsinfo_one_rb1:
                setTabSelection(0);
                break;
            case R.id.detailsinfo_one_rb2:
                setTabSelection(1);
                break;
            case R.id.detailsinfo_back:
                this.finish();
                break;
            case R.id.detailsinfo_one_jiagouwuche:
                if (addAdapter != null && addAdapter.getSeclection() != -1) {
                    jiesuan(pkId, list.get(addAdapter.getSeclection()).getSpecOne(), list.get(addAdapter.getSeclection()).getSpecTwo(), Integer.parseInt(tv_shuliang.getText().toString()));

                } else {
                    ToastUtil.showShort(getApplicationContext(), "请先选择规格！");
                }
                break;
//                购物车
            case R.id.detailsinfo_one_gouwuche:

                intent.setClass(DetailsActivity.this, ShopCarActicity.class);
                startActivity(intent);
                break;
//                添加购物车
            case R.id.detailsinfo_one_addShop:
                if (addAdapter != null && addAdapter.getSeclection() != -1) {
                    String url = URLS.addShopShow();
                    FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                            .add("productId", pkId + "")
                            .add("specOne", list.get(addAdapter.getSeclection()).getSpecOne() + "")
                            .add("specTwo", list.get(addAdapter.getSeclection()).getSpecTwo() + "")
                            .add("count", count + "").build();
                    OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
                        @Override
                        public void onSuccess(BaseBean response) {
                            if (response.getResult() == 1) {
                                ToastUtil.showShort(getApplicationContext(), "添加成功！！！");
                            } else {

                                ToastUtil.showShort(getApplicationContext(), "添加失败！！！");
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {

                            ToastUtil.showShort(getApplicationContext(), "请求失败！！！" + e.getMessage());
                        }
                    });
                } else {
                    ToastUtil.showShort(getApplicationContext(), "请先选择规格");
                }
                break;
            case R.id.guige:
                showBottomdialog();
                break;
            default:
                break;
        }
    }

    /**
     * 选择商品的规格
     */
    private void showBottomdialog() {
        list = new ArrayList<>();
        final Dialog bottomDialog = new Dialog(DetailsActivity.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.add_gouwuche, null);
        bottomDialog.setContentView(contentView);

        gouwuche_img = (RoundImageView) contentView.findViewById(R.id.gouwuche_img);
        gouwuche_money = (TextView) contentView.findViewById(R.id.gouwuche_money);
        gouwuche_kucun = (TextView) contentView.findViewById(R.id.gouwuche_kucun);
        iv_jian = (TextView) contentView.findViewById(R.id.iv_jian);
        tv_shuliang = (TextView) contentView.findViewById(R.id.tv_shuliang);
        iv_jia = (TextView) contentView.findViewById(R.id.iv_jia);
        submit = (TextView) contentView.findViewById(R.id.submit);
        close = contentView.findViewById(R.id.close);
        gridView = contentView.findViewById(R.id.grid);
        addAdapter = new AddAdapter(DetailsActivity.this, list);
        gridView.setAdapter(addAdapter);
        submit = (TextView) contentView.findViewById(R.id.submit);
        add = (TextView) contentView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addAdapter.getSeclection() != -1) {
                    String url = URLS.addShopShow();
                    FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                            .add("productId", pkId + "")
                            .add("specOne", list.get(addAdapter.getSeclection()).getSpecOne() + "")
                            .add("specTwo", list.get(addAdapter.getSeclection()).getSpecTwo() + "")
                            .add("count", count + "").build();
                    OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
                        @Override
                        public void onSuccess(BaseBean response) {
                            if (response.getResult() == 1) {
                                bottomDialog.dismiss();
                                ToastUtil.showShort(getApplicationContext(), "添加成功！！！");
                                bottomDialog.dismiss();
                            } else {

                                ToastUtil.showShort(getApplicationContext(), "添加失败！！！");
                                bottomDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Exception e) {
                            bottomDialog.dismiss();
                            ToastUtil.showShort(getApplicationContext(), "请求失败！！！" + e.getMessage());
                        }
                    });
                } else {
                    ToastUtil.showShort(getApplicationContext(), "请先选择规格");
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addAdapter.setSeclection(position);
                addAdapter.notifyDataSetChanged();
                int seclection = addAdapter.getSeclection();
                if (seclection != -1) {
                    gouwuche_money.setText(list.get(position).getDatalist().get(0).getMoney() + "");
                    gouwuche_kucun.setText("库存" + list.get(position).getDatalist().get(0).getStock() + "件");
                    String picUrl = list.get(position).getDatalist().get(0).getPicUrl();
                    if (!picUrl.startsWith("https://") && !picUrl.startsWith("http://")) {
                        picUrl = URLS.HTTP + picUrl;
                    }
                    Picasso.with(DetailsActivity.this).load(picUrl).fit().error(R.mipmap.logo_zhanwei).into(gouwuche_img);//图片

                }
            }
        });

        String url1 = URLS.guige(pkId);
        OkHttpUtils.get(url1, new OkHttpUtils.ResultCallback<ShangpinguigeBean>() {
            @Override
            public void onSuccess(ShangpinguigeBean response) {

                if (response.getResult() == 1) {
                    List<ShangpinguigeBean.DataBean> data = response.getData();
                    if (data.size() <= 0) {
                        return;
                    }
                    List<ShangpinguigeBean.DataBean.ItemlistBean> itemlist = data.get(0).getItemlist();
                    if (itemlist != null && itemlist.size() > 0) {
                        list.addAll(itemlist);
                        addAdapter.notifyDataSetChanged();
                        guigelist.clear();
                        guigelist.addAll(data);
                        if (guigelist.size() > 0 && guigelist != null) {
                            for (int i = 0; i < guigelist.size(); i++) {
                                String xiaoimage = guigelist.get(i).getItemlist().get(i).getDatalist().get(i).getPicUrl();
                                Picasso.with(DetailsActivity.this).load(URLS.HTTP + xiaoimage).fit().error(R.mipmap.logo_zhanwei).into(gouwuche_img);//图片
                                gouwuche_kucun.setText("库存" + guigelist.get(i).getItemlist().get(i).getDatalist().get(i).getStock() + "件");//库存
                                gouwuche_money.setText("￥" + guigelist.get(i).getItemlist().get(i).getDatalist().get(i).getMoney());//价格
                            }
                        }

                    } else {
                        ToastUtil.showShort(getApplicationContext(), "暂无规格");
                    }


                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "获取失败");
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        iv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cCount = Integer.parseInt(tv_shuliang.getText().toString());
                if (cCount == 1) {
                    return;
                }
                cCount--;
                tv_shuliang.setText(cCount + "");
                count = cCount;
            }
        });

        iv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentCount = Integer.parseInt(tv_shuliang.getText().toString());
                currentCount++;
                tv_shuliang.setText(currentCount + "");
                count = currentCount;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addAdapter.getSeclection() != -1) {
                    jiesuan(pkId, list.get(addAdapter.getSeclection()).getSpecOne(), list.get(addAdapter.getSeclection()).getSpecTwo(), Integer.parseInt(tv_shuliang.getText().toString()));
                    bottomDialog.dismiss();
                } else {
                    ToastUtil.showShort(getApplicationContext(), "请先选择规格");
                }

            }
        });

        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

    }


    private void getmorenaddress() {
        String url = URLS.myAddressShow(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddressOne>() {
            @Override
            public void onSuccess(AddressOne response) {
                if (response.getResult() == 1) {
                    List<AddressOne.DataBean> data = response.getData();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getStatus() == 1) {
                            pikid = data.get(i).getId();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void setTabSelection(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if (tuWenFragment == null) {
                    tuWenFragment = new TuWenFragment();
                    ft.add(R.id.detailsinfo_one_fl, tuWenFragment);
                } else {
                    ft.show(tuWenFragment);
                }
                break;
            case 1:
                if (gouMaiFragment == null) {
                    gouMaiFragment = new GouMaiFragment();
                    ft.add(R.id.detailsinfo_one_fl, gouMaiFragment);
                }
                ft.show(gouMaiFragment);
                break;
        }
        ft.commit();
    }

    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft) {
        if (tuWenFragment != null) {
            ft.hide(tuWenFragment);
        }
        if (gouMaiFragment != null) {
            ft.hide(gouMaiFragment);
        }
    }


    // 指引页面更改事件监听器
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {
            // 遍历数组让当前选中图片下的小圆点设置颜色
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.drawable.fen1);

                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(R.drawable.hui);
                }
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
