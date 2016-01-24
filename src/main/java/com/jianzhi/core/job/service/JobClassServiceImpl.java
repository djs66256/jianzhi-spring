package com.jianzhi.core.job.service;

import com.jianzhi.core.job.dao.JobClassDao;
import com.jianzhi.core.job.model.JobClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobClassServiceImpl implements JobClassService {

    @Autowired
    private JobClassDao jobClassDao;

    @Override
    public void save(JobClass jobClass) {
        jobClassDao.save(jobClass);
    }

    @Override
    public List<JobClass> findAll() {
        return jobClassDao.findAll();
    }
}
