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
import com.zx.zhuangxiu.adapter.WorkerListviewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.ShouYeZgrOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class WorkerFragment extends Fragment implements ItemClickListener {

    private ListView mListView;
    private WorkerListviewAdapter workerListviewAdapter;

    private List<ShouYeZgrOne.DataBean> shouYeZgrTwoList = new ArrayList<>();
    private int userID;
    private ShouYeZgrOne.DataBean dataBean;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum= dataBean.getNumber();
            switch (msg.what){
                case 100:
                    upnum++;
                    dataBean.setNumber(upnum);
                    workerListviewAdapter.update(msg.arg1,mListView,dataBean.getNumber());
                    break;
                case 101:
                    upnum--;
                    dataBean.setNumber(upnum);
                    workerListviewAdapter.update(msg.arg1,mListView,dataBean.getNumber());
                    Toast.makeText(getActivity(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SmartRefreshLayout mRefresh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.worker_fragment, container, false);

        userID = URLS.getUser_id();

        initView(view);
        getInfo();
        return view;
    }

    private void initView(View view) {
        mListView = view.findViewById(R.id.worker_fragment_listview);
        workerListviewAdapter = new WorkerListviewAdapter(getActivity(), shouYeZgrTwoList,WorkerFragment.this);
        mListView.setAdapter(workerListviewAdapter);
        mRefresh = view.findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(getActivity()).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getInfo();
            }
        });

    }

    private void getInfo() {
        String url = URLS.fxFoundWorkerShow(URLS.getUser_id(), 0);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ShouYeZgrOne>() {
            @Override
            public void onSuccess(ShouYeZgrOne response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    shouYeZgrTwoList.clear();
                    shouYeZgrTwoList.addAll(response.getData());
                    workerListviewAdapter.notifyDataSetChanged();

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

        dataBean = (ShouYeZgrOne.DataBean) mListView.getItemAtPosition(postion);
        dianZanInfo(postion);

    }

    /**
     * 点赞
     * @param postion
     */
    private void dianZanInfo( final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getUserId() + "")
                .add("type", 2 + "")
                .build();
        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<DianzanBean>() {
            @Override
            public void onSuccess(DianzanBean response) {
                Message message=Message.obtain();
                if (response.getData().getState() == 1) {
                    message.what=100;
                    message.arg1=postion;
                    handler.sendMessage(message);
                }else {
                    message.what=101;
                    message.arg1=postion;
                    handler.sendMessage(message);
                }
            }
            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getActivity(), "点赞失败！");
            }
        });

    }
}
