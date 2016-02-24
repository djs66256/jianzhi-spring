package com.jianzhi.core.search.model;

/**
 * Created by daniel on 16/2/24.
 */
public class MapItem {
    public static final int MAP_ITEM_TYPE_NONE = 0;
    public static final int MAP_ITEM_TYPE_PERSON = 1;
    public static final int MAP_ITEM_TYPE_COMPANY = 2;

    private double longitude; //经度
    private double latitude; //维度

    private int type = MAP_ITEM_TYPE_NONE;
    private Long uid;
    private Long cid;
    private String avatar;
    private String title;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
