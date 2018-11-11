package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.CommentAdapter;
import com.zx.zhuangxiu.model.CommentsBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mBack;
    private TextView mSend;
    private ListView mListView;
    private int dataID;
    private int typeID;
    List<CommentsBean.DataBean> list =new ArrayList<>();
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        dataID = getIntent().getIntExtra("dataID", -1);
        typeID = getIntent().getIntExtra("type", -1);

        mBack = findViewById(R.id.back);
        mSend = findViewById(R.id.send);
        mListView = findViewById(R.id.listview);
        mBack.setOnClickListener(this);
        mSend.setOnClickListener(this);
        getData(typeID,dataID);
        commentAdapter = new CommentAdapter(this,list);
        mListView.setAdapter(commentAdapter);

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.send:
                Intent intent = new Intent(CommentsActivity.this, SendActivity.class);
                intent.putExtra("da",dataID);
                intent.putExtra("type",typeID);
                startActivityForResult(intent,100);
                break;
        }
    }

    private void getData(int type, int id){
        String pingLun = URLS.getPingLun(type,id);
        OkHttpUtils.get(pingLun, new OkHttpUtils.ResultCallback<CommentsBean>() {
            @Override
            public void onSuccess(CommentsBean response) {
            if (response.getResult()==1){
                List<CommentsBean.DataBean> data = response.getData();
                if (data!=null){
                    list.clear();
                    Collections.reverse(data);
                    list.addAll(data);
                    commentAdapter.notifyDataSetChanged();
                }

            }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            getData(typeID,dataID);
        }
    }
}
