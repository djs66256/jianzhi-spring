package com.jianzhi.core.job.service;

import com.jianzhi.core.job.dao.JobDao;
import com.jianzhi.core.job.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 16/1/4.
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Override
    public void save(Job job) {
        jobDao.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobDao.findByIdAndActive(id, true);
    }

    @Override
    public void delete(Job job) {
        job.setActive(false);
        save(job);
    }
}
