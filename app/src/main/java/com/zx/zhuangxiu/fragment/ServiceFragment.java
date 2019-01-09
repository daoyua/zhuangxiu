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
import android.widget.TextView;
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
import com.zx.zhuangxiu.utils.CustomProgressDialog;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页中的找服务显示的ListView的Fragment
 */
public class ServiceFragment extends Fragment implements ItemClickListener {

    private ListView mListView;
    private FuWuListViewAdapter mFuWuListViewAdapter;  //首页中的找产品与发现中的找产品是一个ListView的Adapter


    private TextView mTitle;
    private List<SyFuWuss.DataBean.PageBean> syFuWuTwoList = new ArrayList<>();

    private CustomProgressDialog progressDialog = null;//声明自定义弹出框
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
                    mFuWuListViewAdapter.update(msg.arg1, mListView, dataBean.getThumbsup());
                    break;
                case 101:
                    upnum--;
                    dataBean.setThumbsup(upnum);
                    mFuWuListViewAdapter.update(msg.arg1, mListView, dataBean.getThumbsup());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private String name;
    private int typeid;
    private SmartRefreshLayout mRefresh;
    private String addTime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.service_fragment, container, false);


        Bundle bundle = getArguments();
        //接收传递过来的值
//        position = bundle.getInt("name");
        typeid = bundle.getInt("type");
        name = bundle.getString("name");
        addTime = bundle.getString("addTime");

        userID = URLS.getUser_id();
//        Log.d("xxx", "找服务====接收type---"+type);

        initView(v);
        return v;
    }


    private void initView(View v) {

        progressDialog = CustomProgressDialog.createDialog(getActivity());

        mTitle = (TextView) v.findViewById(R.id.title);
        mTitle.setText("当前位置:" + name);

        mListView = v.findViewById(R.id.service_fragment_listview);
        mRefresh = v.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getListInfo(typeid);
            }
        });
        getListInfo(typeid);
        progressDialog.show();
    }


    private void getListInfo(int type) {
        String url = URLS.syFuWuShow(typeid);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyFuWuss>() {
            @Override
            public void onSuccess(SyFuWuss response) {
                progressDialog.cancel();
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    syFuWuTwoList.clear();
                    List<SyFuWuss.DataBean.PageBean> page = response.getData().getPage();
//                    Collections.reverse(page);
                    syFuWuTwoList.addAll(page);
                    mFuWuListViewAdapter = new FuWuListViewAdapter(getActivity(), syFuWuTwoList, userID, ServiceFragment.this);
                    mListView.setAdapter(mFuWuListViewAdapter);
                    mFuWuListViewAdapter.notifyDataSetChanged();
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
        dataBean = (SyFuWuss.DataBean.PageBean) mListView.getItemAtPosition(postion);
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
