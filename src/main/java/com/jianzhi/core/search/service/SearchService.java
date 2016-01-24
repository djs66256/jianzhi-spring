package com.jianzhi.core.search.service;

import com.jianzhi.core.search.model.JobFilterItem;
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
}
