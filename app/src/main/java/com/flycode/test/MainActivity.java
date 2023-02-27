package com.flycode.test;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import com.flycode.openapi.map.OpenMapUtil;

public class MainActivity extends AppCompatActivity {
    private static final String LAT_MAP = "39.93349";
    private static final String LONG_MAP = "116.540863";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void openMap(View view) {
        OpenMapUtil.openMapPopupWindow(this,view,"目的地名称",LAT_MAP,LONG_MAP);
    }


}
