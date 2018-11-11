package com.zx.zhuangxiu.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zx.zhuangxiu.fragment.GoodsFragment;
import com.zx.zhuangxiu.model.DaxiaofenleiBean;

import java.util.List;

public class GoodsViewpagerAdapter extends FragmentPagerAdapter{
    private FragmentManager mfragmentManager;
    private List<DaxiaofenleiBean.DataBean> mList;

    public GoodsViewpagerAdapter(FragmentManager fm, List<DaxiaofenleiBean.DataBean> list) {
        super(fm);
        this.mList = list;
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
        GoodsFragment goodsFragment = new GoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",mList.get(position).getCname());
        bundle.putInt("typeid",mList.get(position).getId());

      /*  int weizhi = position+1;
        switch(position) {
            case 0:
                bundle.putInt("name",1);
                break;
            case 1:
                bundle.putInt("name",2);
                break;
            case 2:
                bundle.putInt("name",3);
            break;
        }*/

        goodsFragment.setArguments(bundle);
        return goodsFragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
