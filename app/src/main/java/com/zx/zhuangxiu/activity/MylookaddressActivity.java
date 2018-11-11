package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.LokkaddressAdapter;
import com.zx.zhuangxiu.model.LookaddressBean;

import java.util.ArrayList;
import java.util.List;

public class MylookaddressActivity extends AppCompatActivity {

    private TextView backtext;
    private ListView dizhiaddresslist;

    private List<LookaddressBean.DataBean> mlist = new ArrayList<>();
    private LokkaddressAdapter lokkaddressAdapter;
    private RelativeLayout tainjialayout;
    private TextView tianjiatext;
    private LookaddressBean.DataBean dataBean;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getshouhuodizhi();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylookaddress);

        backtext = findViewById(R.id.mylookaddress_back);
        dizhiaddresslist = findViewById(R.id.mylookaddress_listview);
        tainjialayout = findViewById(R.id.mylookaddress_tianjia);
        tianjiatext = findViewById(R.id.mylookaddress_tianjias);
        backtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        //跳转到添加地址页面
        tainjialayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MylookaddressActivity.this,AddAddressActivity.class);
                startActivityForResult(intent,1000);
            }
        });
        tianjiatext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MylookaddressActivity.this,AddAddressActivity.class);
                startActivityForResult(intent,1000);
            }
        });
        getshouhuodizhi();

        dizhiaddresslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("name",mlist.get(position).getName());
                intent.putExtra("address",mlist.get(position).getDetaladdress());
                int addressid =  mlist.get(position).getId();
                intent.putExtra("addressid",addressid);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        lokkaddressAdapter = new LokkaddressAdapter(MylookaddressActivity.this,mlist);
        dizhiaddresslist.setAdapter(lokkaddressAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }

    /**
     * 获取收货地址
     */
    private void getshouhuodizhi(){

        int userid = URLS.getUser_id();
        String url = URLS.chakandizhi(userid);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<LookaddressBean>() {
            @Override
            public void onSuccess(LookaddressBean response) {

                if (response.getResult() == 1){
                    mlist.clear();
                    mlist.addAll(response.getData());
                    lokkaddressAdapter.notifyDataSetChanged();
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

            if (resultCode ==RESULT_OK) {
                getshouhuodizhi();
            }

      /*  switch (requestCode){
            case 1000:
                getshouhuodizhi();
                break;
        }*/
    }
}
