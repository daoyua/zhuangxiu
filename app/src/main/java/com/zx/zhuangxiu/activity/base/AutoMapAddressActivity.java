package com.zx.zhuangxiu.activity.base;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.zx.zhuangxiu.R;

import java.util.ArrayList;

/**
 * 地址选择器，通过高德地图
 */
public class AutoMapAddressActivity extends AppCompatActivity implements View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener, EditText.OnEditorActionListener {


    MapView mMapView = null;
    AMap aMap;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private EditText auto_edit;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_map_address);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        initview();
        initmap();

    }

    private void initGeocodeSearch(Location location) {
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        LatLonPoint latLonPoint = new LatLonPoint(location.getLatitude(), location.getLongitude());
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);
    }

    private void initview() {
        auto_edit = findViewById(R.id.auto_edit);
        auto_edit.setOnEditorActionListener(this);
        findViewById(R.id.auto_back).setOnClickListener(this);
        findViewById(R.id.auto_save).setOnClickListener(this);
    }

    private void initmap() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomGesturesEnabled(true);

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLocation = location;
                Toast.makeText(AutoMapAddressActivity.this, location.getLongitude() + ":" + location.getLatitude(), Toast.LENGTH_LONG).show();
                initGeocodeSearch(myLocation);//坐标转地址
            }
        });

        CameraUpdate mCameraUpdat = CameraUpdateFactory.zoomTo(14);
        aMap.moveCamera(mCameraUpdat);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.auto_save:
                if (myLocation == null) {
                    Toast.makeText(AutoMapAddressActivity.this, "没有定位到", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();

                myLocation.getLongitude();
                intent.putExtra("lat", myLocation.getLatitude());
                intent.putExtra("lon", myLocation.getLongitude());
                setResult(888, intent);

            case R.id.auto_back:
                finish();
                break;
        }
    }

    //坐标转地址
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
        auto_edit.setText(regeocodeAddress.getFormatAddress());
        Toast.makeText(AutoMapAddressActivity.this, regeocodeAddress.getFormatAddress(), Toast.LENGTH_LONG).show();
    }

    //地址转坐标
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                searchAddress(v.getText().toString());
                break;
            case EditorInfo.IME_ACTION_SEND:
                System.out.println("send a email: " + v.getText());
                break;
            case EditorInfo.IME_ACTION_DONE:
                System.out.println("action done for number_content: " + v.getText());
                break;
        }

        return true;
    }

    private void searchAddress(String add) {
        PoiSearch.Query query = new PoiSearch.Query(add, "", "");
//keyWord表示搜索字符串，
//第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
//cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                ArrayList<PoiItem> pois = poiResult.getPois();
                Toast.makeText(AutoMapAddressActivity.this, pois.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {
                Toast.makeText(AutoMapAddressActivity.this, poiItem.toString(), Toast.LENGTH_LONG).show();
            }
        });
        poiSearch.searchPOIAsyn();
    }
}
