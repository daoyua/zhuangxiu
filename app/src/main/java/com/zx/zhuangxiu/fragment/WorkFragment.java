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
import com.zx.zhuangxiu.adapter.FoundWorkListViewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.ShouYeZgzOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class WorkFragment extends Fragment implements ItemClickListener {

    private ListView mWorkListView;
    private FoundWorkListViewAdapter mWorkListViewAdapter;
    private List<ShouYeZgzOne.DataBean> shouYeZgzTwoList = new ArrayList<>();
    private ShouYeZgzOne.DataBean dataBean;

    private int userID;
    private SmartRefreshLayout mRefresh;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getNumber();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setNumber(upnum);
                    mWorkListViewAdapter.update(msg.arg1, mWorkListView, dataBean.getNumber());
                    break;
                case 101:
                    upnum--;
                    dataBean.setNumber(upnum);
                    mWorkListViewAdapter.update(msg.arg1, mWorkListView, dataBean.getNumber());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.work_fragment, container, false);

        userID = URLS.getUser_id();

        InitView(view);
        return view;
    }

    private void InitView(View view) {
        mWorkListView = view.findViewById(R.id.work_Listview);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getWorkInfo();
            }
        });
        getWorkInfo();
    }

    private void getWorkInfo() {
        String url = URLS.syFoundWorkShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ShouYeZgzOne>() {
            @Override
            public void onSuccess(ShouYeZgzOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    shouYeZgzTwoList.clear();
                    shouYeZgzTwoList.addAll(response.getData());
                    mWorkListViewAdapter = new FoundWorkListViewAdapter(getActivity(), shouYeZgzTwoList, WorkFragment.this);
                    mWorkListView.setAdapter(mWorkListViewAdapter);
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
    public void itemClick(int postion, View view) {
        dataBean = (ShouYeZgzOne.DataBean) mWorkListView.getItemAtPosition(postion);
        dianZanInfo(postion);

    }

    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 3 + "")
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
