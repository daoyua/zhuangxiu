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
import com.zx.zhuangxiu.activity.FabushangjiActivity;
import com.zx.zhuangxiu.adapter.JmhzListViewAdapter;
import com.zx.zhuangxiu.model.BusinessJmhzBean;

import java.util.ArrayList;
import java.util.List;

public class BusinessJmhzFragment extends Fragment {

    private ListView mJmhzListview;
    private JmhzListViewAdapter mJmhzListViewAdapter;
    private List<BusinessJmhzBean.DataBean> jmhzTwoList = new ArrayList<>();
    private TextView jmhztextview;
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.business_jmhz_fragment, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {

        mJmhzListview = view.findViewById(R.id.jmhz_listview);
        jmhztextview = view.findViewById(R.id.jmhz_hezuo);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getJmhzInfo();
            }
        });
        jmhztextview.setVisibility(View.GONE);
        jmhztextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FabushangjiActivity.class);
                startActivity(intent);
            }
        });


        getJmhzInfo();

    }

    private void getJmhzInfo() {
        String url = URLS.sjJmhzShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<BusinessJmhzBean>() {
            @Override
            public void onSuccess(BusinessJmhzBean response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    jmhzTwoList.clear();
                    jmhzTwoList = response.getData();
                    mJmhzListViewAdapter = new JmhzListViewAdapter(getActivity(), jmhzTwoList);
                    mJmhzListview.setAdapter(mJmhzListViewAdapter);
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
