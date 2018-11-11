package com.zx.zhuangxiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.NoticeListViewAdapter;
import com.zx.zhuangxiu.model.XiTongOne;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页-消息-系统消息
 * */
public class XiaoXiNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView notice_listview;
    private NoticeListViewAdapter noticeListViewAdapter;

    private List<XiTongOne.DataBean> xiTongTwoList = new ArrayList<>();

    private TextView notice_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi_notice);

        initView();
    }

    private void initView() {
        notice_back = (TextView) findViewById(R.id.notice_back);
        notice_back.setOnClickListener(this);
        notice_listview = (ListView) findViewById(R.id.notice_listview);
        getListContent();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        this.finish();
    }

    public void getListContent() {
        String url = URLS.xiTongShow();
//        Log.d("xxx", "--------"+url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<XiTongOne>() {
            @Override
            public void onSuccess(XiTongOne response) {
                if(response.getResult() == 1){
                    xiTongTwoList.addAll(response.getData());

                    noticeListViewAdapter = new NoticeListViewAdapter(XiaoXiNoticeActivity.this, xiTongTwoList);
                    notice_listview.setAdapter(noticeListViewAdapter);
                }else {
                    Toast.makeText(XiaoXiNoticeActivity.this, "暂无系统通知！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.notice_back:
                setResult(RESULT_OK);
                this.finish();
                break;
        }
    }
}
