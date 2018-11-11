package com.zx.zhuangxiu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.AddressListViewAdapter;
import com.zx.zhuangxiu.model.AddressOne;
import com.zx.zhuangxiu.model.AddressTwo;
import com.zx.zhuangxiu.model.ChengGong;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView my_address_back, my_address_add;
    private ListView my_address_listview;
    private AddressListViewAdapter addressListViewAdapter;
    private String dingdan;//结算界面携带数据
    private float heji;//结算页面携带数据
    private int tap;//跳转标识符
    private List<AddressTwo> addressTwoList = new ArrayList<>();
    private SmartRefreshLayout mRefresh;

    //点击没一条，出现2个选项，确认，回跳来源界面。删除，删除此条信息。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        Bundle bundle = getIntent().getExtras();
        dingdan = bundle.getString("dingdan");
        heji = bundle.getFloat("heji");
        tap = bundle.getInt("tap");

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getAddressInfo();
    }

    private void initView() {
        my_address_back = (TextView) findViewById(R.id.my_address_back);
        my_address_add = (TextView) findViewById(R.id.my_address_add);
        mRefresh = findViewById(R.id.refresh);
        mRefresh.setRefreshHeader(new ClassicsHeader(this).setEnableLastTime(true));
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
//                getAddressInfo();
            }
        });


        my_address_back.setOnClickListener(this);
        my_address_add.setOnClickListener(this);

        my_address_listview = (ListView) findViewById(R.id.my_address_listview);
//        getAddressInfo();
        my_address_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MyAddressActivity.this);
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("地址", position + "确定");
                        switch (tap) {
                            case 0://从我的跳转进来，无需操作
                                break;
                            case 1://从订单跳转
                                Intent intent= new Intent(MyAddressActivity.this,JieSuanActivity.class);
                                Bundle bundle= new Bundle();
                                bundle.putFloat("heji",heji);
                                bundle.putString("dingdan",dingdan);
                                bundle.putString("address",addressTwoList.get(position).getAddress());
                                bundle.putString("addressname",addressTwoList.get(position).getUserName()+"  "+addressTwoList.get(position).getMobile());
                                bundle.putInt("pikid",addressTwoList.get(position).getPkId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case 2:
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("地址", position + "删除");
                        String url = URLS.deldteaddress(addressTwoList.get(position).getPkId());
                        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChengGong>() {//复用实体类
                            @Override
                            public void onSuccess(ChengGong response) {
                                if (response.getId() == 0) {
                                    Toast.makeText(MyAddressActivity.this, "删除成功", Toast.LENGTH_LONG).show();
//                                    getAddressInfo();
                                } else {
                                    Toast.makeText(MyAddressActivity.this, "删除失败", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });

                    }
                });
                builder.create().show();
            }
        });
    }

    /*private void getAddressInfo() {
        String url = URLS.myAddressShow(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddressOne>() {
            @Override
            public void onSuccess(AddressOne response) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
                if (response.getResult() == 0) {
                    addressTwoList.clear();
                    addressTwoList = response.getData();
                    addressListViewAdapter = new AddressListViewAdapter(MyAddressActivity.this, addressTwoList);
                    my_address_listview.setAdapter(addressListViewAdapter);
                } else {
                    Toast.makeText(MyAddressActivity.this, "很抱歉，" + response.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (mRefresh!=null){
                    mRefresh.finishRefresh();
                }
            }
        });
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_address_back:
                this.finish();
                break;
            case R.id.my_address_add:
                Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
