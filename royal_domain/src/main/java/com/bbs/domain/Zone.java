package com.bbs.domain;

/**
 * 交流区板块
 */
public class Zone {
    //交流区编号
    private int zoneId;
    //交流区名字
    private String zoneName;
    //是否默认，1代表默认，2代表非默认
    private int isDef;

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getIsDef() {
        return isDef;
    }

    public void setIsDef(int isDef) {
        this.isDef = isDef;
    }
}
