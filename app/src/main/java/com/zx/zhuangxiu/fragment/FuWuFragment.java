package com.zx.zhuangxiu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.FuWuListViewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.SyFuWuss;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class FuWuFragment extends Fragment implements ItemClickListener {

    private ListView mFuWuListView;
    private FuWuListViewAdapter mFuWuListViewAdapter;
    private List<SyFuWuss.DataBean.PageBean> syFuWuTwoList = new ArrayList<>();
    private int userID;

    private SyFuWuss.DataBean.PageBean dataBean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getThumbsup();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setThumbsup(upnum);
                    mFuWuListViewAdapter.update(msg.arg1, mFuWuListView, dataBean.getThumbsup());
                    break;
                case 101:
                    upnum--;
                    dataBean.setThumbsup(upnum);
                    mFuWuListViewAdapter.update(msg.arg1, mFuWuListView, dataBean.getThumbsup());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SmartRefreshLayout mRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fuwu_fragment, container, false);

        initView(view);
        userID = URLS.getUser_id();
        return view;
    }

    private void initView(View view) {
        mFuWuListView = (ListView) view.findViewById(R.id.fuwu_listview);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getListInfo();
            }
        });
        getListInfo();

    }

    private void getListInfo() {
        String url = URLS.fxFuWuShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyFuWuss>() {
            @Override
            public void onSuccess(SyFuWuss response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }

                if (response.getResult() == 1) {
                    syFuWuTwoList.clear();
                    syFuWuTwoList.addAll(response.getData().getPage());
                    mFuWuListViewAdapter = new FuWuListViewAdapter(getActivity(), syFuWuTwoList, userID, FuWuFragment.this);
                    mFuWuListView.setAdapter(mFuWuListViewAdapter);
                    mFuWuListViewAdapter.notifyDataSetChanged();
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

    @Override
    public void itemClick(int postion, View view) {
        dataBean = (SyFuWuss.DataBean.PageBean) mFuWuListView.getItemAtPosition(postion);
        dianZanInfo(postion);

    }

    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 1 + "")
                .build();
        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean response) {
                Message message = Message.obtain();
                if (response.getData().getState() == 1) {
                    message.what = 100;
                    message.arg1 = postion;
                    handler.sendMessage(message);
                } else {
                    message.what = 101;
                    message.arg1 = postion;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getContext(), "点赞失败！");
            }
        });

    }
}
