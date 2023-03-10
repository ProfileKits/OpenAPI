package com.flycode.openapi.map;

import java.util.List;

public class MapOptions {
    String startName;
    String endName;
    String startLat;
    String startLng;
    String endLat;
    String endLng;
    String navType;//交通工具类型
    List<LatLng> latLngs;//途经地
    boolean isOnlyShowEnableTripartiteMap;//只显示已安装的三方地图菜单
    String notMapTips;//未安装的三方地图菜单提示

    public String getNotMapTips() {
        return notMapTips;
    }

    public void setNotMapTips(String notMapTips) {
        this.notMapTips = notMapTips;
    }

    public boolean isOnlyShowEnableTripartiteMap() {
        return isOnlyShowEnableTripartiteMap;
    }

    public void setOnlyShowEnableTripartiteMap(boolean onlyShowEnableTripartiteMap) {
        isOnlyShowEnableTripartiteMap = onlyShowEnableTripartiteMap;
    }

    public List<LatLng> getLatLngs() {
        return latLngs;
    }

    public void setLatLngs(List<LatLng> latLngs) {
        this.latLngs = latLngs;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLng() {
        return endLng;
    }

    public void setEndLng(String endLng) {
        this.endLng = endLng;
    }

    public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
    }

    public MapOptions() {
    }

    public MapOptions(String startName, String endName, String startLat, String startLng, String endLat, String endLng, String navType) {
        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.navType = navType;
    }

    public MapOptions(String startName, String endName, String startLat, String startLng, String endLat, String endLng, String navType, List<LatLng> latLngs) {
        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.navType = navType;
        this.latLngs = latLngs;
    }

    public static class LatLng{
        private String latitude;
        private String longitude;
        private String name;

        public LatLng(String latitude, String longitude, String name) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

    public MapOptions(String startName, String endName, String startLat, String startLng, String endLat, String endLng, String navType, List<LatLng> latLngs, boolean isOnlyShowEnableTripartiteMap) {
        this.startName = startName;
        this.endName = endName;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.navType = navType;
        this.latLngs = latLngs;
        this.isOnlyShowEnableTripartiteMap = isOnlyShowEnableTripartiteMap;
    }
}
