package com.zx.zhuangxiu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.zx.zhuangxiu.activity.OrderActivity;
import com.zx.zhuangxiu.adapter.MyzhunbeisxAdapter;
import com.zx.zhuangxiu.model.MyOrderBean;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ZhengZaijxFragment extends Fragment {


    private View view;
    private ListView mjinxinglistview;
    private List<MyOrderBean.DataBean> mlist = new ArrayList<>();
    private MyzhunbeisxAdapter myzhunbeisxAdapter;
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.zhengzaijx_fragment, container, false);

        //找listviewid
        mjinxinglistview = view.findViewById(R.id.zhengzaijinxing_list);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getzhengzaijinxing();
            }
        });
        getzhengzaijinxing();
        mjinxinglistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                intent.putExtra("orderId",mlist.get(position).getId());
                startActivity(intent);
                startActivityForResult(intent,1000);
            }
        });
        return view;
    }

    private void getzhengzaijinxing() {
        String url = URLS.MyOrder(URLS.getUser_id(), 3);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MyOrderBean>() {
            @Override
            public void onSuccess(MyOrderBean response) {
                mlist.clear();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    if (response.getData().size()>0&&response.getData()!=null){

                        mlist.addAll(response.getData());
                        myzhunbeisxAdapter = new MyzhunbeisxAdapter(getActivity(), mlist);
                        mjinxinglistview.setAdapter(myzhunbeisxAdapter);
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
            getzhengzaijinxing();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK ){
            if (requestCode==1000){
                getzhengzaijinxing();
            }
        }
    }
}
