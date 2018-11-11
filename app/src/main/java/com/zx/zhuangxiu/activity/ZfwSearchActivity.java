package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.FuWuListViewAdapter;
import com.zx.zhuangxiu.adapter.SouSuoAdapter;
import com.zx.zhuangxiu.fragment.ServiceFragment;
import com.zx.zhuangxiu.model.SyFuWuss;
import com.zx.zhuangxiu.model.SySearchOne;
import com.zx.zhuangxiu.model.SySearchTwo;

import java.util.ArrayList;
import java.util.List;

public class ZfwSearchActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView ss_back;
    private EditText ss_et;
    private TextView ss_ss, ss_no;
    private ListView mListview;
    private SouSuoAdapter souSuoAdapter;

    private String title;
    private List<SyFuWuss.DataBean.PageBean> sySearchTwoList = new ArrayList<>();
    private FuWuListViewAdapter mFuWuListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfw_search);

        initView();
    }

    private void initView() {
        ss_back = (TextView) findViewById(R.id.ss_back);
        ss_et = (EditText)findViewById(R.id.ss_et);
        ss_ss = (TextView)findViewById(R.id.ss_ss);
        ss_no = (TextView)findViewById(R.id.ss_no);
        mListview = (ListView)findViewById(R.id.ss_listview);


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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ss_back:
                this.finish();
                break;
            case R.id.ss_ss:
                sySearchTwoList.clear();
                title = ss_et.getText().toString();
                getSearchInfo();
                break;
        }
    }

    private void getSearchInfo() {
        if(title.equals("")){
            ss_no.setVisibility(View.VISIBLE);
            Toast.makeText(ZfwSearchActivity.this, "请输入服务再搜索！", Toast.LENGTH_SHORT).show();
            return;
        }else {
            String url = URLS.syFwSearchShow(title);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<SyFuWuss>() {
                @Override
                public void onSuccess(SyFuWuss response) {
                    if(response.getResult() == 1){
                        sySearchTwoList = response.getData().getPage();

                        if (sySearchTwoList.size() == 0){
                            ss_no.setVisibility(View.VISIBLE);
                        }else {
                            ss_no.setVisibility(View.GONE);
                        }
                        mFuWuListViewAdapter = new FuWuListViewAdapter(ZfwSearchActivity.this, sySearchTwoList);
                        mListview.setAdapter(mFuWuListViewAdapter);
                        mFuWuListViewAdapter.notifyDataSetChanged();
                     /*   souSuoAdapter = new SouSuoAdapter(ZfwSearchActivity.this, sySearchTwoList);
                        mListview.setAdapter(souSuoAdapter);*/
                    }else {
                        ss_no.setVisibility(View.VISIBLE);
                        Toast.makeText(ZfwSearchActivity.this, "非常抱歉，没有您要搜索的服务！", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }

    }


}
