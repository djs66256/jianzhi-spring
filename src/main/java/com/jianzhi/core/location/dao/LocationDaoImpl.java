package com.jianzhi.core.location.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by daniel on 2016/1/18.
 */
public class LocationDaoImpl implements LocationDaoCustom {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void findByLocation(double minLng, double maxLng, double minLat, double maxLat) {
        Query query = this.entityManager.createNativeQuery(
                "select address.id as aid, company.id as cid from address "+
                        "LEFT JOIN company ON address.company=company.id");
        List list = query.getResultList();

        for (Object o : list) {
            Class cls = o.getClass();

            Logger.getGlobal().log(Level.ALL, cls.toString());
        }

        return;
    }
}
