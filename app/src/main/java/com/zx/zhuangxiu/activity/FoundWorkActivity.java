package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.zx.zhuangxiu.adapter.FoundWorkListViewAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.ShouYeZgzOne;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页-找工作
 * <p>
 * 2018-08-30  修改了这个页面
 * 注释了一个方法
 */
public class FoundWorkActivity extends AppCompatActivity implements View.OnClickListener,ItemClickListener {

    private ImageView work_sousuo_img;
    private ListView mListView;
    private TextView work_back, work_fabu;
    private EditText work_dizhi_et, work_zhiwei_et;

    private String dizhi = "", zhiwei = "";

    private FoundWorkListViewAdapter foundWorkListViewAdapter;

    private List<ShouYeZgzOne.DataBean> shouYeZgzOneList = new ArrayList<>();

    private ShouYeZgzOne.DataBean dataBean;

    private int userID;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int upnum= dataBean.getNumber();
            switch (msg.what){
                case 100:
                    upnum++;
                    dataBean.setNumber(upnum);
                    foundWorkListViewAdapter.update(msg.arg1,mListView,dataBean.getNumber());
                    break;
                case 101:
                    upnum--;
                    dataBean.setNumber(upnum);
                    foundWorkListViewAdapter.update(msg.arg1,mListView,dataBean.getNumber());
                    Toast.makeText(getApplicationContext(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private SmartRefreshLayout mRefresh;
    private TextView bt_provid_work;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_work);

        userID = URLS.getUser_id();

        initView();
    }

    private void initView() {
        work_back = (TextView) findViewById(R.id.work_back);
        work_fabu = (TextView) findViewById(R.id.work_fabu);
        work_sousuo_img = (ImageView) findViewById(R.id.work_sousuo_img);
        work_dizhi_et = (EditText) findViewById(R.id.work_dizhi_et);
        work_zhiwei_et = (EditText) findViewById(R.id.work_zhiwei_et);

        work_sousuo_img.setOnClickListener(this);
        work_fabu.setOnClickListener(this);
        work_back.setOnClickListener(this);
        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getWorkInfo();
            }
        });

        mListView = findViewById(R.id.shouye_work_listview);


        getWorkInfo();
    }

    /**
     * 获取找工作列表
     */
    private void getWorkInfo() {
        String url = URLS.syFoundWorkShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ShouYeZgzOne>() {
            @Override
            public void onSuccess(ShouYeZgzOne response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 1) {
                    shouYeZgzOneList.clear();
                    shouYeZgzOneList.addAll(response.getData());
                    foundWorkListViewAdapter = new FoundWorkListViewAdapter(FoundWorkActivity.this, shouYeZgzOneList,FoundWorkActivity.this);
                    mListView.setAdapter(foundWorkListViewAdapter);
                } else {
                    Toast.makeText(FoundWorkActivity.this, "很抱歉，找工作暂无数据！", Toast.LENGTH_SHORT).show();
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }



    private void Search(String searchName, String city) {
        String url = URLS.syFoundWorkShow(searchName, city);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ShouYeZgzOne>() {
            @Override
            public void onSuccess(ShouYeZgzOne response) {
                if (response.getResult() == 1) {
                    if (response.getData().size() > 0) {
                        shouYeZgzOneList.clear();
                        shouYeZgzOneList.addAll(response.getData());
                        foundWorkListViewAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(FoundWorkActivity.this, "很抱歉，找工作暂无数据！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.work_back:
                this.finish();
                break;

            case R.id.work_fabu:
                intent.setClass(FoundWorkActivity.this, FaBuWorkActivity.class);
                startActivityForResult(intent,1000);
                break;
            case R.id.work_sousuo_img:
                dizhi = work_dizhi_et.getText().toString();
                zhiwei = work_zhiwei_et.getText().toString();
                Search(zhiwei, dizhi);
                break;
        }
    }

    @Override
    public void itemClick(int postion, View view) {
        dataBean = (ShouYeZgzOne.DataBean) mListView.getItemAtPosition(postion);
        dianZanInfo(postion);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode==RESULT_OK){
//            switch (requestCode){
//                case 1000:
//                    getWorkInfo();
//                    break;
//            }
//        }
//    }

    /**
     * 点赞
     * @param postion
     */
    private void dianZanInfo( final int postion) {
        String url = URLS.dianzan();
        FormBody formBody = new FormBody.Builder().add("userId", userID + "")
                .add("objectId", dataBean.getId() + "")
                .add("type", 3 + "")
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
                ToastUtil.showShort(getApplicationContext(), "点赞失败！");
            }
        });

    }
}