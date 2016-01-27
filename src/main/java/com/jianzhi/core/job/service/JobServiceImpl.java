package com.jianzhi.core.job.service;

import com.jianzhi.core.job.dao.JobDao;
import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.job.model.JobDetailInfo;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return jobDao.findOne(id);
    }

    @Override
    public List<Job> findByUser(User user) {
        return jobDao.findByUser(user);
    }

    @Override
    public void delete(Job job) {
        jobDao.delete(job);
    }

    @Override
    public JobDetailInfo findDetailInfo(Long id) {
        return jobDao.findDetailInfoOne(id);
    }
}
