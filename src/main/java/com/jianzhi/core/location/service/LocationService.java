package com.jianzhi.core.location.service;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.location.model.Location;
import com.jianzhi.core.user.model.User;

import java.util.List;

/**
 * Created by daniel on 2016/1/17.
 */
public interface LocationService {
    void save(Location address);

    List<Location> find();
    Location findByCompany(Company company);
    Location findByUser(User user);
}
