package com.zx.zhuangxiu.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class ConversationList extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);

        TextView mBack= findViewById(R.id.back);
        TextView mTitle= findViewById(R.id.title);
/*        //实现会话列表,如果想实现会话列表就加这个代码
        String sName = getIntent().getData().getQueryParameter("name");//获取昵称
        setTitle("与" + sName + "聊天中");*/
        mTitle.setText("会话");

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}