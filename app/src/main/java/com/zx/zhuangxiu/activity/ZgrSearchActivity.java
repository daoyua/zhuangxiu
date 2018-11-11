package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.ItemClickListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.WorkerListviewAdapter;
import com.zx.zhuangxiu.adapter.ZgrSearchAdapter;
import com.zx.zhuangxiu.model.DianzanBean;
import com.zx.zhuangxiu.model.ShouYeZgrOne;
import com.zx.zhuangxiu.model.ZgrSearchOne;
import com.zx.zhuangxiu.model.ZgrSearchTwo;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

/**
 * 首页-找工人-搜索界面
 */
public class ZgrSearchActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private TextView ss_back;
    private EditText ss_et;
    private TextView ss_ss, ss_no;
    private ListView mListview;

    private String title;
    private List<ShouYeZgrOne.DataBean> zgrSearchTwoList = new ArrayList<>();

    private ZgrSearchAdapter zgrSearchAdapter;
    private WorkerListviewAdapter workerListviewAdapter;


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
                    workerListviewAdapter.update(msg.arg1, mListview, dataBean.getNumber());
                    break;
                case 101:
                    upnum--;
                    dataBean.setNumber(upnum);
                    workerListviewAdapter.update(msg.arg1, mListview, dataBean.getNumber());
                    Toast.makeText(getApplicationContext(), "取消点赞！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgr_search);
        userID = URLS.getUser_id();
        initView();
    }


    private void initView() {
        ss_back = (TextView) findViewById(R.id.ss_back);
        ss_et = (EditText) findViewById(R.id.ss_et);
        ss_ss = (TextView) findViewById(R.id.ss_ss);
        ss_no = (TextView) findViewById(R.id.ss_no);
        mListview = (ListView) findViewById(R.id.ss_listview);


        ss_back.setOnClickListener(this);
        ss_ss.setOnClickListener(this);

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

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ss_back:
                this.finish();
                break;
            case R.id.ss_ss:
                zgrSearchTwoList.clear();
                title = ss_et.getText().toString();
                getSearchInfo();
                break;
        }
    }

    private void getSearchInfo() {
        if (title.equals("")) {
            ss_no.setVisibility(View.VISIBLE);
            Toast.makeText(ZgrSearchActivity.this, "请输入工人或工种再搜索！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            String url = URLS.syZgrSearchShow(title);
            Log.d("xxx", "找工人搜索--------" + url);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ShouYeZgrOne>() {
                @Override
                public void onSuccess(ShouYeZgrOne response) {
                    if (response.getResult() == 1) {
                        zgrSearchTwoList = response.getData();

                        if (zgrSearchTwoList.size() == 0) {
                            ss_no.setVisibility(View.VISIBLE);
                        } else {
                            ss_no.setVisibility(View.GONE);
                        }
                        workerListviewAdapter = new WorkerListviewAdapter(ZgrSearchActivity.this, zgrSearchTwoList, ZgrSearchActivity.this);
                        mListview.setAdapter(workerListviewAdapter);

                    } else {
                        ss_no.setVisibility(View.VISIBLE);
                        Toast.makeText(ZgrSearchActivity.this, "非常抱歉，没有您要搜索的工人或工种！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }
    }

    @Override
    public void itemClick(int postion, View view) {
        dataBean = (ShouYeZgrOne.DataBean) mListview.getItemAtPosition(postion);
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
