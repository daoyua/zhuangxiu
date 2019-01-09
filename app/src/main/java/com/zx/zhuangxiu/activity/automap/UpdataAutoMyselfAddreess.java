package com.zx.zhuangxiu.activity.automap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.model.AddressService;
import com.zx.zhuangxiu.model.MySelfInfoBean;

public class UpdataAutoMyselfAddreess extends Activity implements View.OnClickListener {

    private TextView auto_myslef_txt;
    private String latitude;
    private String longitude;
    private String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_auto_myself_addreess);
        initview();
        getAutoAddress();
    }

    private void initview() {
        auto_myslef_txt = findViewById(R.id.auto_myslef_txt);
        findViewById(R.id.auto_address_back).setOnClickListener(this);
        findViewById(R.id.auto_myslef_rl).setOnClickListener(this);
        findViewById(R.id.auto_address_modify).setOnClickListener(this);
    }

    private void getAutoAddress() {
        String url = URLS.mydata(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<MySelfInfoBean>() {


            @Override
            public void onSuccess(MySelfInfoBean response) {

                if (response.getResult() == 1) {
                    MySelfInfoBean.Data data = response.getData();
                    add = data.getAddress();
                    latitude = data.getLatitude();
                    longitude = data.getLongitude();
                    auto_myslef_txt.setText(add);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private final int ADDRESS = 222;// 选择地址

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto_address_back:
                finish();
                break;
            case R.id.auto_myslef_rl://选择地图
                Intent intent = new Intent(UpdataAutoMyselfAddreess.this, AutoMapAddressActivity.class);
                startActivityForResult(intent, ADDRESS);
                break;
            case R.id.auto_address_modify://修改
                if (!TextUtils.isEmpty(add)) {
                    updateAddress(add, longitude, latitude);
                }

                break;
        }
    }

    private void updateAddress(String add, final String lon, final String lat) {
        String ss = URLS.updateAddress(add, lon, lat);


        OkHttpUtils.get(ss, new OkHttpUtils.ResultCallback<AddressService>() {
            @Override
            public void onSuccess(AddressService response) {
                Toast.makeText(UpdataAutoMyselfAddreess.this, "修改成功", Toast.LENGTH_SHORT).show();
//                Toast.makeText(HomeActivity.this, response.getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(UpdataAutoMyselfAddreess.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //系统回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 表示 调用照相机拍照
            case ADDRESS://地址
                if (resultCode == 888) {
//                    intent.putExtra("lat", myLocation.getLatitude());
//                    intent.putExtra("lon", myLocation.getLongitude());
//                    intent.putExtra("add", auto_edit.getText());
                    Bundle extras = data.getExtras();
                    add = extras.getString("add");
                    latitude = extras.getString("lat");
                    longitude = extras.getString("lon");
                    if (!TextUtils.isEmpty(add)) {
                        auto_myslef_txt.setText(add);
                    }

//                   Double lat= data.getDoubleExtra("lon",0);
                }
                break;
        }
    }
}
