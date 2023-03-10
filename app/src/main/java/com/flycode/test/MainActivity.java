package com.flycode.test;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.flycode.openapi.map.MapOptions;
import com.flycode.openapi.map.OpenMapUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LAT_MAP = "39.93349";//起点
    private static final String LONG_MAP = "116.540863";

    private static final String LAT_MAP2 = "38.93349";//途径点1
    private static final String LONG_MAP2 = "115.540863";

    private static final String LAT_MAP3 = "38.73349";//途径点2
    private static final String LONG_MAP3 = "115.940863";


    private static final String LAT_MAP4 = "37.93349";//终点
    private static final String LONG_MAP4 = "117.590863";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openMap(View view) {
        //第一种调用方式
//        OpenMapUtil.openMapPopupWindow(this,view,"起点地名称","目的地名称",LAT_MAP,LONG_MAP,LAT_MAP2,LONG_MAP2);
        //第二种调用方式
        MapOptions mapOptions = new MapOptions();
        mapOptions.setStartLat(LAT_MAP);
        mapOptions.setStartLng(LONG_MAP);
        mapOptions.setStartName("起点点");
        mapOptions.setEndLat(LAT_MAP4);
        mapOptions.setEndLng(LONG_MAP4);
        mapOptions.setEndName("终点点");
        mapOptions.setOnlyShowEnableTripartiteMap(true);
        //不推荐加途径点，因为途径点需要和三方地图商务洽谈付费实现途径点功能，所以这种加了途径点也是不会起作用的
//        List<MapOptions.LatLng> wayPoints = new ArrayList<>();
//        wayPoints.add(new MapOptions.LatLng(LAT_MAP2, LONG_MAP2,"途径1"));
//        wayPoints.add(new MapOptions.LatLng(LAT_MAP3, LONG_MAP3,"途径2"));
//        mapOptions.setLatLngs(wayPoints);
        OpenMapUtil.openMapPopupWindow(this, view, mapOptions);
    }

}
