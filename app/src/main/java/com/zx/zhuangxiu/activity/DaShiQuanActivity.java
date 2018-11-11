package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.DaShiQuanAdapter;
import com.zx.zhuangxiu.model.DaShiQuanLieBiaoTwo;
import com.zx.zhuangxiu.model.ToutiaoBena;

import java.util.ArrayList;
import java.util.List;

public class DaShiQuanActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView back;
    private ListView toutiao_listView;

    private List<DaShiQuanLieBiaoTwo> daShiQuanLieBiaoTwoList = new ArrayList<>();
    private DaShiQuanAdapter daShiQuanAdapter;
    private List<ToutiaoBena.DataBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_shi_quan);

        initView();
    }

    private void initView() {
        back = (TextView) findViewById(R.id.toutiao_back);
        back.setOnClickListener(this);

        toutiao_listView = (ListView)findViewById(R.id.toutiao_listView);

        getDaShiQuanInfo();

        toutiao_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DaShiQuanActivity.this, DsqDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pkId", mlist.get(i).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    //获取大时圈头条信息
    private void getDaShiQuanInfo() {
        String url = URLS.daShiQuanShow();
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ToutiaoBena>() {
            @Override
            public void onSuccess(ToutiaoBena response) {

                if(response.getResult() == 1){
                    mlist.addAll(response.getData());
                    daShiQuanAdapter = new DaShiQuanAdapter(DaShiQuanActivity.this, mlist);
                    toutiao_listView.setAdapter(daShiQuanAdapter);
                }else {
                    Toast.makeText(DaShiQuanActivity.this, "非常抱歉，大时圈头条没有数据", Toast.LENGTH_SHORT).show();
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
            case R.id.toutiao_back:
                this.finish();
                break;
        }
    }
}
