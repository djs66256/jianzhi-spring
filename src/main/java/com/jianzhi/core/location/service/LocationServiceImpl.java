package com.jianzhi.core.location.service;

import com.jianzhi.core.location.dao.LocationDao;
import com.jianzhi.core.location.model.Location;
import com.sun.tools.javac.file.Locations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public void save(Location location) {
        locationDao.save(location);
    }
}
