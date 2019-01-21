package com.zx.zhuangxiu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.utils.GeoCodeUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements AMapLocationListener {

    private MapView mMapView;
    private AMap mAMap;
    MyLocationStyle myLocationStyle;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private String map;
    private float lat;
    private float lon;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 10:
                    MarkerOptions markerOptions = new MarkerOptions();
                    //添加一个位置--经度，维度---marker对应一个markerOptions，用来设置marker的属性等
                    markerOptions.position(new LatLng(lat, lon));
                    markerOptions.draggable(false);
                    //添加图标
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker));
                    ;
                    //添加marker
                    Marker marker = mAMap.addMarker(markerOptions);
                    mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15));
                    break;
            }
        }
    };
    private String mudilat;
    private String mudilon;
    private double mulon;
    private double mulat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = getIntent().getStringExtra("map");
        mudilat = getIntent().getStringExtra("lat");
        mudilon = getIntent().getStringExtra("lon");
        //获取地图控件引用
        initView(savedInstanceState);

try {

    String lons = getIntent().getStringExtra("lon");
    if(TextUtils.isEmpty(lons)){
        location();
    }else{
        mulon = Double.valueOf(lons);
        String lats = getIntent().getStringExtra("lat");
        mulat = Double.valueOf(lats);
        updateMap(map,mulat,mulon);
    }


}catch (Exception e){
    Toast.makeText(MapActivity.this, "没有传坐标", Toast.LENGTH_SHORT).show();
}

    }

    private LatLonPoint currentLatLng = new LatLonPoint(0, 0);

    private void updateMap(String s, double latitude, double longitude) {
        currentLatLng.setLatitude(latitude);
        currentLatLng.setLongitude(longitude);
//        auto_edit.setText(s);
        LatLng latLonPoint1 = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLonPoint1);
        CameraUpdate cameraUpdate1 = CameraUpdateFactory.zoomTo(14);
        //TODO
        mAMap.animateCamera(cameraUpdate);
        mAMap.animateCamera(cameraUpdate1);

        mAMap.animateCamera(cameraUpdate);
        LatLng latLng = new LatLng(latitude, longitude);
        Marker marker = mAMap.addMarker(new MarkerOptions().position(latLng).title(s).snippet("DefaultMarker"));
    }

    private void initView(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();



//        location();




        findViewById(R.id.my_info_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mudilat!=null){
                    IntenMap(mudilat, mudilon);
                }else{
                    IntenMap(lat+"", lon+"");
                }

            }
        });

    }


    private void IntenMap(String lat, String lon) {
        if (isAvilible(this, "com.autonavi.minimap")) {
            try {
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=null&poiname=" + "" + "&lat="
                        + lat
                        + "&lon="
                        + lon + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "您尚未安装高德地图", Toast.LENGTH_LONG)
                    .show();
            Uri uri = Uri
                    .parse("market://details?id=com.autonavi.minimap");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    public static boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    public void location() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.location)));
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.setMyLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        mLocationOption = new AMapLocationClientOption();
//初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(this);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();
        GeoCodeUtils.GeocodeSearch(map, this, new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                if (i == AMapException.CODE_AMAP_SUCCESS) {
                    if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                            && geocodeResult.getGeocodeAddressList().size() > 0) {
                        GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
                        //获取到的经纬度
                        LatLonPoint latLongPoint = address.getLatLonPoint();
                        lat = (float) latLongPoint.getLatitude();
                        lon = (float) latLongPoint.getLongitude();
                        Message obtain = Message.obtain();
                        obtain.what = 10;
                        handler.sendMessage(obtain);
                    }
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if(mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
            handler.removeMessages(10);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                Log.e("TAG", "11111");

//                mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.90403, 116.407525),8));
            } else {
                Log.e("TAG", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
}
