package com.zx.zhuangxiu.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zx.zhuangxiu.fragment.ServiceFragment;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;

import java.util.List;

public class FuWuViewpager2Adapter extends FragmentPagerAdapter {

    private List<DaxiaofenleiBean.DataBean> mList;
    private int type;

    public FuWuViewpager2Adapter(FragmentManager fm, List<DaxiaofenleiBean.DataBean> list, int type) {
        super(fm);
        this.mList = list;
        this.type = type;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getCname();
    }

    @Override
    public Fragment getItem(int position) {
        //一般我们在这个位置对比一下标题是什么,,,然后返回对应的fragment
        //初始化fragment  对应position有多少，fragment有多少
        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",mList.get(position).getCname());
        bundle.putInt("type",mList.get(position).getId());
       /* if (mList.get(position).equals("装修建材")){
            bundle.putInt("name",1);
            bundle.putInt("type", type);
        }else if (mList.get(position).equals("家政服务")){
            bundle.putInt("name",2);
            bundle.putInt("type", type);
        }else if (mList.get(position).equals("其他")){
            bundle.putInt("name",3);
            bundle.putInt("type", type);
        }*/
        //给fragment 加bundle 数据
        //activity与fragment 1.getset，2.接口回调，3.setArguments ,getAraguments
        serviceFragment.setArguments(bundle);
        return serviceFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
