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
    public static void openMapPopupWindow(final Activity activity, View root, final String endName, final String endLat, final String endLon) {
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
                toBaidu(activity, endName, endLat, endLon);
                mPopup.dismiss();
            }
        });

        btn_openGaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toGaode(activity, endName, endLat, endLon);
                mPopup.dismiss();
            }
        });

        btn_openTencent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTengxun(activity, endName, endLat, endLon);
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

    private static String mEndName = "";

    //打开外部地图
    public static void openMapPopupWindow(final Activity activity, View root, final String startName, final String endName, final String startLat, final String startLon, final String endLat, final String endLon) {
        mEndName = endName;
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
                toBaidu(activity, startName, startLat, startLon, endLat, endLon);
                mPopup.dismiss();
            }
        });

        btn_openGaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toGaode(activity, startName, startLat, startLon, endLat, endLon);
                mPopup.dismiss();
            }
        });

        btn_openTencent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toTengxun(activity, startName, startLat, startLon, endLat, endLon);
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

    private static void toGaode(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.autonavi.minimap")) {
            goToNaviActivity(activity, title, latitude, longitude, "", "");
        } else {
            Toast.makeText(activity, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }
    

    private static void toBaidu(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.baidu.BaiduMap")) {
            goToBaiduActivity(activity, title, latitude, longitude, "", "");
        } else {
            Toast.makeText(activity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    private static void toTengxun(Activity activity, String title, String latitude, String longitude) {
        Intent intent;
        if (isAvilible(activity, "com.tencent.map")) {
            gotoTengxun(activity, title, latitude, longitude, "", "");
        } else {
            Toast.makeText(activity, "您尚未安装腾讯地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.tencent.map");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }


    private static void toGaode(Activity activity, String title, final String startLat, final String startLon, final String endLat, final String endLon) {
        Intent intent;
        if (isAvilible(activity, "com.autonavi.minimap")) {
            goToNaviActivity(activity, title, startLat, startLon, endLat, endLon);
        } else {
            Toast.makeText(activity, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    private static void toBaidu(Activity activity, String title, final String startLat, final String startLon, final String endLat, final String endLon) {
        Intent intent;
        if (isAvilible(activity, "com.baidu.BaiduMap")) {
            goToBaiduActivity(activity, title, startLat, startLon, endLat, endLon);
        } else {
            Toast.makeText(activity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
            Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
            intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    private static void toTengxun(Activity activity, String title, final String startLat, final String startLon, final String endLat, final String endLon) {
        Intent intent;
        if (isAvilible(activity, "com.tencent.map")) {
            gotoTengxun(activity, title, startLat, startLon, endLat, endLon);
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
    private static boolean isAvilible(Context context, String packageName) {
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
     * @param startLat 必填 纬度
     * @param startLon 必填 经度
     */
    private static void goToNaviActivity(Context context, String startName, String startLat, String startLon, String endLat, String endLon) {
        if (endLat.isEmpty()) {
            Uri uri = Uri.parse("amapuri://route/plan/?did=BGVIS2&dlat=" + startLat + "&dlon=" + startLon + "&dname=" + startName + "&dev=0&t=3");
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.setPackage("com.autonavi.minimap");
            context.startActivity(intent);
        } else {
            Uri uri = Uri.parse("amapuri://route/plan/?sid=BGVIS1&slat=" + startLat + "&slon=" + startLon + "&sname=" + startName + "&did=BGVIS2&dlat=" + endLat + "&dlon=" + endLon + "&dname=" + mEndName + "&dev=0&t=3");
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.setPackage("com.autonavi.minimap");
            context.startActivity(intent);
        }

    }

    /**
     * 启动百度App进行导航
     *
     * @param startLat 必填 纬度
     * @param startLon 必填 经度
     */
    private static void goToBaiduActivity(Context context, String name, String startLat, String startLon, String endLat, String endLon) {
        if (endLat.isEmpty()) {
            //百度地图
            Uri uri = Uri.parse("baidumap://map/direction?" +
                    "destination=latlng:" + startLat + "," + startLon + "|name:" + name + //终点
                    "&mode=riding&" +          //导航路线方式
                    "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

            Intent naviIntent = new Intent("android.intent.action.VIEW", uri);
            context.startActivity(naviIntent);
        } else {
            //百度地图
            Uri uri = Uri.parse("baidumap://map/direction?" +
                    "origin=latlng:" + startLat + "," + startLon + "|name:" + name +
                    "&destination=latlng:" + endLat + "," + endLon + "|name:" + mEndName + //终点
                    "&mode=riding&" +          //导航路线方式
                    "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

            Intent naviIntent = new Intent("android.intent.action.VIEW", uri);
            context.startActivity(naviIntent);
        }

    }

    /**
     * 启动腾讯地图App进行导航
     *
     * @param startLat 必填 纬度
     * @param startLon 必填 经度
     */
    private static void gotoTengxun(Context context, String name, String startLat, String startLon, String endLat, String endLon) {
        // 腾讯地图
        if (endLat.isEmpty()) {
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=bike&from=&fromcoord=&to=" + name + "&tocoord=" + startLat + "," + startLon + "&policy=0&referer=appName"));
            context.startActivity(naviIntent);
        } else {
            Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=bike&from=" + name + "&fromcoord=" + startLat + "," + startLon + "&to=" + mEndName + "&tocoord=" + endLat + "," + endLon + "&policy=0&referer=appName"));
            context.startActivity(naviIntent);
        }

    }
}
