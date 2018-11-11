package com.zx.zhuangxiu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.GmfwListViewAdapter;
import com.zx.zhuangxiu.model.BusinessGmfwBean;
import com.zx.zhuangxiu.model.WorkerListItem;

import java.util.ArrayList;
import java.util.List;

public class BusinessGmfwFragment extends Fragment {

    private ListView mGmfwListview;
    private GmfwListViewAdapter mGmfwListViewAdapter;
    private WorkerListItem workListItems;
    private List<BusinessGmfwBean.DataBean> workList = new ArrayList<>();
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.business_gmfw_fragment, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mGmfwListview = view.findViewById(R.id.gmfw_listview);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });

    }

    private void initData() {
        String url = URLS.goumaifuwuShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BusinessGmfwBean>() {
            @Override
            public void onSuccess(BusinessGmfwBean response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    workList.clear();
                    workList.addAll(response.getData());
                    mGmfwListViewAdapter = new GmfwListViewAdapter(getActivity(), workList);
                    mGmfwListview.setAdapter(mGmfwListViewAdapter);
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

}
