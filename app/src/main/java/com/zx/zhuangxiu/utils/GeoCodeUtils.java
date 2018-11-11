package com.zx.zhuangxiu.utils;

import android.content.Context;

import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeSearch;

public class GeoCodeUtils {

    private static GeocodeSearch geocodeSearch;


    public  static void   GeocodeSearch(String city, Context context,GeocodeSearch.OnGeocodeSearchListener listener){
    //构造 GeocodeSearch 对象，并设置监听。
     geocodeSearch = new GeocodeSearch(context);
     geocodeSearch.setOnGeocodeSearchListener(listener);
        GeocodeQuery query = new GeocodeQuery(city, city);
        geocodeSearch.getFromLocationNameAsyn(query);
/*     geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            Log.e("TAG","i"+i);
            if (i == AMapException.CODE_AMAP_SUCCESS) {
                if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null
                        && geocodeResult.getGeocodeAddressList().size() > 0) {
                    GeocodeAddress address = geocodeResult.getGeocodeAddressList().get(0);
                    //获取到的经纬度
                    LatLonPoint latLongPoint = address.getLatLonPoint();
                float    Lat = (float)latLongPoint.getLatitude();
                  float  Lon = (float)latLongPoint.getLongitude();
                    latLng = new LatLng(Lat,Lon);
                }
            }
        }
    });*/


}
}
