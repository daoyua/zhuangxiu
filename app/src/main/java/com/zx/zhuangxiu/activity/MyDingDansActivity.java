package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.fragment.YiwanchengFragment;
import com.zx.zhuangxiu.fragment.ZhengZaijxFragment;
import com.zx.zhuangxiu.fragment.ZhunbeisxFragment;

public class MyDingDansActivity extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup dindangroup;
    private RadioButton button1;
    private View button2;
    private View button3;
    private FrameLayout container;

    public static final int ITEM_zhunbeishixiang = 0;   //准备事项
    public static final int ITEM_zhengzaijinxng = 1; // 正在进行
    public static final int ITEM_yiwancheng = 2; // 已完成
    private int mCurrentItem;
    private ZhunbeisxFragment mzhunbeifrag;
    private ZhengZaijxFragment jinxingfrag;
    private YiwanchengFragment yiwanchengfrag;
    private Fragment frag = new ZhunbeisxFragment();
    private ImageView dingdanback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHomeFragment(savedInstanceState);
        setContentView(R.layout.activity_my_ding_dans);

        initview();
        //显示的那一页
        switchItem(ITEM_zhunbeishixiang);

    }

    private void initview() {

        dindangroup = findViewById(R.id.dingdans_radiogroup);
        button1 = findViewById(R.id.dingdans_radiobutton1);
        button2 = findViewById(R.id.dingdans_radiobutton2);
        button3 = findViewById(R.id.dingdans_radiobutton3);
        container = findViewById(R.id.dingdans_container);
        dingdanback = findViewById(R.id.mydingdanback);

        dingdanback.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

    }

    /**
     * 显示隐藏fragment
     * @param itemId
     */
    private void switchItem(int itemId){

        switch (itemId){

            case ITEM_zhunbeishixiang:
                mCurrentItem = ITEM_zhunbeishixiang;
                if (mzhunbeifrag == null) {
                    mzhunbeifrag = new ZhunbeisxFragment();
                }
                switchContent(frag, mzhunbeifrag, "zhunbei");
                frag = mzhunbeifrag;
                break;
            case ITEM_zhengzaijinxng:
                mCurrentItem = ITEM_zhengzaijinxng;
                if (jinxingfrag == null) {
                    jinxingfrag = new ZhengZaijxFragment();
                }
                switchContent(frag, jinxingfrag, "jinxin");
                frag = jinxingfrag;
                break;
            case ITEM_yiwancheng:
                mCurrentItem = ITEM_yiwancheng;
                if (yiwanchengfrag == null) {
                    yiwanchengfrag = new YiwanchengFragment();
                }
                switchContent(frag, yiwanchengfrag, "yiwancheng");
                frag = yiwanchengfrag;
                break;
        }

    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mydingdanback:
                finish();
                break;
            case R.id.dingdans_radiobutton1:
                switchItem(ITEM_zhunbeishixiang);
                break;
            case R.id.dingdans_radiobutton2:
                switchItem(ITEM_zhengzaijinxng);
                break;
            case R.id.dingdans_radiobutton3:
                switchItem(ITEM_yiwancheng);
                break;
        }

    }
    public void switchContent(Fragment from, Fragment to, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (to != null && from != null){
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.dingdans_container, to, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }

    }

    private void showHomeFragment(Bundle savedInstanceState) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState != null) {
            mzhunbeifrag = (ZhunbeisxFragment) fragmentManager.findFragmentByTag("zhunbei");
            jinxingfrag = (ZhengZaijxFragment) fragmentManager.findFragmentByTag("jinxin");
            yiwanchengfrag = (YiwanchengFragment) fragmentManager.findFragmentByTag("yiwancheng");
            fragmentManager.beginTransaction()
                    .show(mzhunbeifrag)
                    .hide(jinxingfrag)
                    .hide(yiwanchengfrag)
                    .commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment zhunbei = getSupportFragmentManager().findFragmentByTag("zhunbei");
        /*然后在碎片中调用重写的onActivityResult方法*/
        zhunbei.onActivityResult(requestCode, resultCode, data);
    }
}
