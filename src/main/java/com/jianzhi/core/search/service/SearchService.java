package com.jianzhi.core.search.service;

import com.jianzhi.core.search.model.JobFilterItem;
import com.jianzhi.core.search.model.MapItem;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SearchService {

    @Autowired
    private EntityManager entityManager;

    public List<JobFilterItem> findByFilter() {
        List<JobFilterItem> results = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT id, title FROM job limit 0,20");
        List<List> list = query.getResultList();
        for (Object o : list) {
            Object[] l = (Object[])o;
            JobFilterItem item = new JobFilterItem();
            BigInteger id = (BigInteger)l[0];
            item.setId(id.longValue());
            item.setTitle((String)l[1]);

            results.add(item);
        }
        return results;
    }

    public List<MapItem> findMapItemsByLocation(double lat, double lon, double range) {
        String sql = "SELECT l.latitude AS lat, l.longitude AS lon, " +
                "u.id AS uid, u.nick_name AS unick_name, u.head_image AS uavatar, " +
                "c.id AS cid, c.name AS cname, cu.id AS cuid, cu.head_image AS cuavatar " +
                "FROM location l " +
                "LEFT JOIN user u ON u.id=l.user " +
                "LEFT JOIN company c on c.id=company " +
                "LEFT JOIN user cu ON cu.id=c.user " +
                "WHERE latitude BETWEEN :minLat AND :maxLat " +
                "AND longitude BETWEEN :minLon AND :maxLon";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("minLat", lat-range);
        query.setParameter("maxLat", lat+range);
        query.setParameter("minLon", lon-range);
        query.setParameter("maxLon", lon+range);
        List list = query.getResultList();
        List<MapItem> mapItemList = new ArrayList<>();
        for (Object o : list) {
            try {
                Object[] l = (Object[]) o;
                MapItem mapItem = new MapItem();
                mapItem.setLatitude((double) l[0]);
                mapItem.setLongitude((double) l[1]);
                Object uid = l[2];
                if (uid != null) {
                    mapItem.setUid(((BigInteger) uid).longValue());
                    mapItem.setType(MapItem.MAP_ITEM_TYPE_PERSON);
                    mapItem.setTitle((String) l[3]);
                    mapItem.setAvatar((String) l[4]);
                }
                Object cid = l[5];
                if (cid != null) {
                    mapItem.setCid(((BigInteger) cid).longValue());
                    mapItem.setType(MapItem.MAP_ITEM_TYPE_COMPANY);
                    mapItem.setTitle((String)l[6]);
                    mapItem.setUid(((BigInteger) l[7]).longValue());
                    mapItem.setAvatar((String)l[8]);
                }
                mapItemList.add(mapItem);
            }
            catch (Exception e) {

            }
        }

        return mapItemList;
    }

    public List<MapItem> findMapJobseekerItemsByLocation(double lat, double lon, double range) {
        String sql = "SELECT l.latitude AS lat, l.longitude AS lon, " +
                "u.id AS uid, u.nick_name AS unick_name, u.head_image AS uavatar " +
                "FROM location l " +
                "JOIN user u ON u.id=l.user " +
                "WHERE u.group_type=:group_type " +
                "AND latitude BETWEEN :minLat AND :maxLat " +
                "AND longitude BETWEEN :minLon AND :maxLon";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("group_type", User.JOBSEEKER);
        query.setParameter("minLat", lat-range);
        query.setParameter("maxLat", lat+range);
        query.setParameter("minLon", lon-range);
        query.setParameter("maxLon", lon+range);
        List list = query.getResultList();
        List<MapItem> mapItemList = new ArrayList<>();
        for (Object o : list) {
            try {
                Object[] l = (Object[]) o;
                MapItem mapItem = new MapItem();
                mapItem.setLatitude((double) l[0]);
                mapItem.setLongitude((double) l[1]);
                Object uid = l[2];
                mapItem.setUid(((BigInteger) uid).longValue());
                mapItem.setType(MapItem.MAP_ITEM_TYPE_PERSON);
                mapItem.setTitle((String) l[3]);
                mapItem.setAvatar((String) l[4]);

                mapItemList.add(mapItem);
            }
            catch (Exception e) {

            }
        }

        return mapItemList;
    }

    public List<MapItem> findMapCompanyItemsByLocation(double lat, double lon, double range) {
        String sql = "SELECT l.latitude AS lat, l.longitude AS lon, " +
                "c.id AS cid, c.name AS cname, cu.id AS cuid, cu.head_image AS cuavatar " +
                "FROM location l " +
                "JOIN company c on c.id=company " +
                "LEFT JOIN user cu ON cu.id=c.user " +
                "WHERE latitude BETWEEN :minLat AND :maxLat " +
                "AND longitude BETWEEN :minLon AND :maxLon";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("minLat", lat-range);
        query.setParameter("maxLat", lat+range);
        query.setParameter("minLon", lon-range);
        query.setParameter("maxLon", lon+range);
        List list = query.getResultList();
        List<MapItem> mapItemList = new ArrayList<>();
        for (Object o : list) {
            try {
                Object[] l = (Object[]) o;
                MapItem mapItem = new MapItem();
                mapItem.setLatitude((double) l[0]);
                mapItem.setLongitude((double) l[1]);
                Object cid = l[2];
                mapItem.setCid(((BigInteger) cid).longValue());
                mapItem.setType(MapItem.MAP_ITEM_TYPE_COMPANY);
                mapItem.setTitle((String)l[3]);
                mapItem.setUid(((BigInteger) l[4]).longValue());
                mapItem.setAvatar((String)l[5]);

                mapItemList.add(mapItem);
            }
            catch (Exception e) {

            }
        }

        return mapItemList;
    }
}
