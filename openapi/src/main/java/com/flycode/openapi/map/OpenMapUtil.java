package com.flycode.openapi.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.flycode.openapi.R;

import java.util.ArrayList;
import java.util.List;

public class OpenMapUtil {

    //打开外部地图
    public static void openMapPopupWindow(final Activity activity, View root, final String title, final String latitude, final String longitude) {
        View view = View.inflate(activity, R.layout.popu_open_map, null);
        final Button btn_openBaidu = view.findViewById(R.id.btn_open_baidu);
        Button btn_openGaode = view.findViewById(R.id.btn_open_gaode);
        Button btn_openTencent = view.findViewById(R.id.btn_open_tencent);
        Button btn_cancel = view.findViewById(R.id.btn_open_cancel);

        final PopupWindow mPopup = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setFocusable(true);
        mPopup.setOutsideTouchable(true);
        mPopup.setBackgroundDrawable(new ColorDrawable());
        mPopup.setAnimationStyle(R.style.popupwindow_style);


        btn_openBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBaidu(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_openGaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toGaode(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_openTencent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTengxun(activity, title, latitude, longitude);
                mPopup.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopup.dismiss();
            }
        });

        mPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeActivityBg(activity, 1f);
            }
        });

        mPopup.showAtLocation(root, Gravity.BOTTOM, 0, 0);

        changeActivityBg(activity, 0.7f);
    }

    //改变activity背景透明度
    private static void changeActivityBg(Activity activity, float f) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = f;// 值越小透明度越暗
        activity.getWindow().setAttributes(params);
    }

    public static void toGaode(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.autonavi.minimap")) {
            goToNaviActivity(activity, title, "", latitude, longitude, "0", "2");
        } else {
            Toast.makeText(activity, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    public static void toBaidu(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.baidu.BaiduMap")) {
            goToBaiduActivity(activity, title, latitude, longitude);
        } else {
            Toast.makeText(activity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    public static void toTengxun(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.tencent.map")) {
            gotoTengxun(activity, title, latitude, longitude);
        } else {
            Toast.makeText(activity, "您尚未安装腾讯地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.tencent.map");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    /*
     * 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 启动高德App进行导航
     *
     * @param sourceApplication 必填 第三方调用应用名称。如 amap
     * @param poiname           非必填 POI 名称
     * @param lat               必填 纬度
     * @param lon               必填 经度
     * @param dev               必填 是否偏移(0:lat 和 lon 是已经加密后的,不需要国测加密; 1:需要国测加密)
     * @param style             必填 导航方式(0 速度快; 1 费用少; 2 路程短; 3 不走高速；4 躲避拥堵；5 不走高速且避免收费；6 不走高速且躲避拥堵；7 躲避收费和拥堵；8 不走高速躲避收费和拥堵))
     */
    public static void goToNaviActivity(Context context, String sourceApplication, String poiname, String lat, String lon, String dev, String style) {
        StringBuffer stringBuffer = new StringBuffer("androidamap://navi?sourceApplication=")
                .append(sourceApplication);
        if (!TextUtils.isEmpty(poiname)) {
            stringBuffer.append("&poiname=").append(poiname);
        }
        stringBuffer.append("&lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&dev=0&t=3&policy=2");

        Uri uri = Uri.parse("amapuri://route/plan/?did=BGVIS2&dlat=" + lat + "&dlon=" + lon + "&dname=" + sourceApplication + "&dev=0&t=3");
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }

    /**
     * 启动百度App进行导航
     *
     * @param lat 必填 纬度
     * @param lon 必填 经度
     */
    public static void goToBaiduActivity(Context context, String name, String lat, String lon) {
        //百度地图
        Uri uri = Uri.parse("baidumap://map/direction?" +
                "destination=latlng:" + lat + "," + lon + "|name:" + name + //终点
                "&mode=riding&" +          //导航路线方式
                "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

        Intent naviIntent = new Intent("android.intent.action.VIEW", uri);
        context.startActivity(naviIntent);
    }

    /**
     * 启动腾讯地图App进行导航
     *
     * @param lat 必填 纬度
     * @param lon 必填 经度
     */
    public static void gotoTengxun(Context context, String name, String lat, String lon) {
        // 腾讯地图
        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=bike&from=&fromcoord=&to=" + name + "&tocoord=" + lat + "," + lon + "&policy=0&referer=appName"));
        context.startActivity(naviIntent);

    }
}
