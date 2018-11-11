package com.zx.zhuangxiu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.activity.MyDingDanGRFuWuActivity;
import com.zx.zhuangxiu.activity.OrderActivity;
import com.zx.zhuangxiu.adapter.MyzhunbeisxAdapter;
import com.zx.zhuangxiu.model.MyOrderBean;
import com.zx.zhuangxiu.model.MydingdansBean;

import java.util.ArrayList;
import java.util.List;

public class ZhunbeisxFragment extends Fragment {


    private ListView zbsxfrag;
    private List<MyOrderBean.DataBean> mlist = new ArrayList<>();
    private MyzhunbeisxAdapter myzhunbeisxAdapter;
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.zhunbeishixiang_fragment, container, false);

        initview(view);


        return view;
    }

    private void initview(View view) {

        zbsxfrag = view.findViewById(R.id.zhunbeishixiangfrag_list);

        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getzhubei();
            }
        });
        getzhubei();
        zbsxfrag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("orderId",mlist.get(position).getId());
                startActivity(intent);
            }
        });

    }

    private void getzhubei() {

        String url = URLS.MyOrder(URLS.getUser_id(),2);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyOrderBean>() {
            @Override
            public void onSuccess(MyOrderBean response) {
                mlist.clear();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    if (response.getData()!=null&&response.getData().size()>0){

                        mlist.addAll(response.getData());
                        myzhunbeisxAdapter = new MyzhunbeisxAdapter(getActivity(), mlist);
                        zbsxfrag.setAdapter(myzhunbeisxAdapter);
                    }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
       if (hidden==false){
           getzhubei();
       }
    }

}
