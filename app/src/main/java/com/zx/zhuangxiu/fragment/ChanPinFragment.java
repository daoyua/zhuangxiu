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
import com.zx.zhuangxiu.adapter.ChanPinlistviewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.SyProductOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 发现-找产品的fragment
 */
public class ChanPinFragment extends Fragment implements ItemClickListener {

    private ListView mChanPinlistview;
    private ChanPinlistviewAdapter mChanPinlistviewAdapter;

    private List<SyProductOne.DataBean> syProductTwoList = new ArrayList<>();
    private int userID;

    private SyProductOne.DataBean dataBean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getUpnum();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setUpnum(upnum);
                    mChanPinlistviewAdapter.update(msg.arg1, mChanPinlistview, dataBean.getUpnum());
                    break;
                case 101:
                    upnum--;
                    dataBean.setUpnum(upnum);
                    mChanPinlistviewAdapter.update(msg.arg1, mChanPinlistview, dataBean.getUpnum());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.chanpin_fragment, container, false);

        userID =URLS.getUser_id();

        initView(view);

        return view;
    }

    private void initView(View view) {
        mChanPinlistview = (ListView) view.findViewById(R.id.chanpin_listview);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getChanPinInfo(0);
            }
        });
        getChanPinInfo(0);

    }

    private void getChanPinInfo(int type) {
        String url = URLS.syChanPinShows(type);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyProductOne>() {
            @Override
            public void onSuccess(SyProductOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    syProductTwoList.clear();
                    syProductTwoList.addAll(response.getData());
                    mChanPinlistviewAdapter = new ChanPinlistviewAdapter(getActivity(), syProductTwoList, ChanPinFragment.this);
                    mChanPinlistview.setAdapter(mChanPinlistviewAdapter);
                    mChanPinlistviewAdapter.notifyDataSetChanged();
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
        dataBean = (SyProductOne.DataBean) mChanPinlistview.getItemAtPosition(postion);
        dianZanInfo(postion);
    }


    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 0 + "")
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
