package com.jianzhi.core.location.service;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.location.dao.LocationDao;
import com.jianzhi.core.location.model.Location;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public void save(Location address) {
        locationDao.save(address);
    }

    @Override
    public List<Location> find() {
//        List<Address> addressList = addressDao.findByLatitudeAndLongitude(10,40,10,40);
//
//        return addressList;
//        addressDao.findByX();
        locationDao.findByLocation(0.0,100.0,0.0,100.0);
        return null;
    }

    @Override
    public @NotNull Location findByCompany(Company company) {
        if (company.getId() != null) {
            Location address = locationDao.findByCompany(company);
            if (address != null) {
                return address;
            }
        }
        Location address = new Location();
        address.setCompany(company);
        return address;
    }

    @Override
    public Location findByUser(User user) {
        return locationDao.findByUser(user);
    }
}
