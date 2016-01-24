package com.jianzhi.core.location.dao;

/**
 * Created by daniel on 2016/1/18.
 */
public interface LocationDaoCustom {
    void findByLocation(double minLng, double maxLng, double minLat, double maxLat);
}
