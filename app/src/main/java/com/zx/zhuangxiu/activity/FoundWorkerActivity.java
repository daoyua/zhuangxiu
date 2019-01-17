package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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
import com.zx.zhuangxiu.adapter.WorkerListviewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.ShouYeZgrOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页-找工人界面
 */
public class FoundWorkerActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private RadioButton worker_rb1, worker_rb2, worker_rb3;
    private ImageView worker_banner;
    private ListView worker_Listview;
    private WorkerListviewAdapter workerListviewAdapter;

    private ImageView worker_search;
    private TextView worker_back;
    private List<ShouYeZgrOne.DataBean> shouYeZgrTwoList = new ArrayList<>();

    private ShouYeZgrOne.DataBean dataBean;

    private int userID;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum = dataBean.getNumber();
            switch (msg.what) {
                case 100:
                    upnum++;
                    dataBean.setNumber(upnum);
                    workerListviewAdapter.update(msg.arg1, worker_Listview, dataBean.getNumber());
                    break;
                case 101:
                    upnum--;
                    dataBean.setNumber(upnum);
                    workerListviewAdapter.update(msg.arg1, worker_Listview, dataBean.getNumber());
                    Toast.makeText(getApplicationContext(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private SmartRefreshLayout mRefresh;
    private int page = 0;
    private TextView worker_fabu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_worker);

        userID = URLS.getUser_id();

        initView();
    }

    private void initView() {
        worker_rb1 = (RadioButton) findViewById(R.id.worker_rb1);
        worker_rb2 = (RadioButton) findViewById(R.id.worker_rb2);
        worker_rb3 = (RadioButton) findViewById(R.id.worker_rb3);
        worker_banner = (ImageView) findViewById(R.id.worker_banner);
        worker_fabu = (TextView) findViewById(R.id.worker_fabu);
        worker_back = (TextView) findViewById(R.id.worker_back);
        worker_search = (ImageView) findViewById(R.id.worker_search);

        worker_fabu.setOnClickListener(this);
        worker_search.setOnClickListener(this);
        worker_rb1.setOnClickListener(this);
        worker_rb2.setOnClickListener(this);
        worker_rb3.setOnClickListener(this);
        worker_banner.setOnClickListener(this);
        worker_back.setOnClickListener(this);

        worker_Listview = findViewById(R.id.worker_Listview);
        getWorkerList(1);

        workerListviewAdapter = new WorkerListviewAdapter(FoundWorkerActivity.this, shouYeZgrTwoList, FoundWorkerActivity.this);
        worker_Listview.setAdapter(workerListviewAdapter);
        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if (page == 1) {
                    getWorkerList(page, URLS.getLocation());
                } else {
                    getWorkerList(page);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.worker_fabu:
                intent.setClass(FoundWorkerActivity.this, FaBuWorkActivity.class);
                startActivity(intent);
                break;
                case R.id.worker_rb1:
                shouYeZgrTwoList.clear();
                getWorkerList(0);
                page = 0;
                break;
            case R.id.worker_rb2:
                shouYeZgrTwoList.clear();
//                getWorkerList(1, URLS.getLocation());
                getWorkerList(1);
                page = 1;
                break;
            case R.id.worker_rb3:
                shouYeZgrTwoList.clear();
                getWorkerList(2);
                page = 2;
                break;
            case R.id.worker_back:
                this.finish();
                break;
            case R.id.worker_search:
                intent.setClass(FoundWorkerActivity.this, ZgrSearchActivity.class);
                startActivity(intent);
                break;
        }
    }


    public void getWorkerList(int typeId) {
        String urlPath = URLS.syFoundWorkerShow(typeId);

        OkHttpUtils.get(urlPath, new OkHttpUtils.ResultCallback<ShouYeZgrOne>() {
            @Override
            public void onSuccess(ShouYeZgrOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    shouYeZgrTwoList.clear();
                    shouYeZgrTwoList.addAll(response.getData());
                    workerListviewAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(FoundWorkerActivity.this, "很抱歉，暂无工人数据!", Toast.LENGTH_SHORT).show();
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

    public void getWorkerList(int typeId, String address) {
        String urlPath = URLS.syFoundWorkerShow(typeId);

        OkHttpUtils.get(urlPath, new OkHttpUtils.ResultCallback<ShouYeZgrOne>() {
            @Override
            public void onSuccess(ShouYeZgrOne response) {
                if (mRefresh != null) {
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    shouYeZgrTwoList.clear();
                    shouYeZgrTwoList.addAll(response.getData());
                    workerListviewAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(FoundWorkerActivity.this, "很抱歉，暂无工人数据!", Toast.LENGTH_SHORT).show();
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
        dataBean = (ShouYeZgrOne.DataBean) worker_Listview.getItemAtPosition(postion);
        dianZanInfo(postion);

    }


    /**
     * 点赞
     *
     * @param postion
     */
    private void dianZanInfo(final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getUserId() + "")
                .add("type", 2 + "")
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
                ToastUtil.showShort(getApplicationContext(), "点赞失败！");
            }
        });

    }
}
