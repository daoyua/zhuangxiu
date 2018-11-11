package com.zx.zhuangxiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.FaBuShngPinActivity;
import com.zx.zhuangxiu.adapter.CgxpListViewAdapter;
import com.zx.zhuangxiu.model.BusinessCgspBean;

import java.util.ArrayList;
import java.util.List;

public class BusinessCgspFragment extends Fragment {

    private ListView cgxp_listview;
    private CgxpListViewAdapter mCgxpListViewAdapter;

    private List<BusinessCgspBean.DataBean> syProductTwoList = new ArrayList<>();
    private SmartRefreshLayout mRefresh;
    private TextView mRelease;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.business_cgsp_fragment, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        cgxp_listview =  view.findViewById(R.id.cgxp_listview);
        mRefresh = view.findViewById(R.id.refresh);
        mRelease = view.findViewById(R.id.tv_release);
        mRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),FaBuShngPinActivity.class));
            }
        });
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getChanPinInfo(0);
            }
        });
        getChanPinInfo(0);
    }


    private void getChanPinInfo(int type) {
        String url = URLS.shangjiChanPinShow(type);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BusinessCgspBean>() {
            @Override
            public void onSuccess(BusinessCgspBean response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if(response.getResult() == 1){
                    syProductTwoList.clear();
                    syProductTwoList.addAll(response.getData());
                    mCgxpListViewAdapter = new CgxpListViewAdapter(getActivity(), syProductTwoList);
                    cgxp_listview.setAdapter(mCgxpListViewAdapter);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
            }
        });
    }


}
