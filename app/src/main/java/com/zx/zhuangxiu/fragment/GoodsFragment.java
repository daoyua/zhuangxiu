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
import com.zx.zhuangxiu.adapter.ChanPinlistviewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.SyProductOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页中的找产品显示的ListView的Fragment
 */
public class GoodsFragment extends Fragment implements ItemClickListener {

    private ListView mListView;
    private ChanPinlistviewAdapter mChanPinlistviewAdapter;  //首页中的找产品与发现中的找产品是一个ListView的Adapter

    private TextView mTitle;
    private String title;
    private List<SyProductOne.DataBean> syProductTwoList = new ArrayList<>();
    private int userID;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getUpnum();
            switch (msg.what) {
                case 100:

                    upnum++;
                    dataBean.setUpnum(upnum);
                    mChanPinlistviewAdapter.update(msg.arg1, mListView, dataBean.getUpnum());
                    break;
                case 101:
                    upnum--;
                    dataBean.setUpnum(upnum);
                    mChanPinlistviewAdapter.update(msg.arg1, mListView, dataBean.getUpnum());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SyProductOne.DataBean dataBean;
    private int typeid;
    private SmartRefreshLayout mRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.goods_fragment, container, false);


        Bundle bundle = getArguments();
        //接收传递过来的值
        title = bundle.getString("name");
        typeid = bundle.getInt("typeid");
        userID = URLS.getUser_id();


        initView(v);
        return v;
    }

    private void initView(View v) {
        mTitle = (TextView) v.findViewById(R.id.title);
        mTitle.setText("当前位置:" + title);
        mListView = v.findViewById(R.id.goods_fragment_listview);

        mRefresh = v.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getListInfo(typeid);
            }
        });
        getListInfo(typeid);
    }

    private void getListInfo(int type) {
        String url = URLS.syChanPinShow(typeid);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyProductOne>() {
            @Override
            public void onSuccess(SyProductOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    syProductTwoList.clear();
                    syProductTwoList = response.getData();
                    mChanPinlistviewAdapter = new ChanPinlistviewAdapter(getActivity(), syProductTwoList, GoodsFragment.this);
                    mListView.setAdapter(mChanPinlistviewAdapter);
                    mChanPinlistviewAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "很抱歉，找产品暂时没有数据", Toast.LENGTH_SHORT).show();
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
        dataBean = (SyProductOne.DataBean) mListView.getItemAtPosition(postion);
        dianZanInfo(postion);
      /* int a= dataBean.getUpnum();
        int b=a+1;
        dataBean.setUpnum(b);
        mChanPinlistviewAdapter.update(postion,mListView, dataBean.getUpnum());*/
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
