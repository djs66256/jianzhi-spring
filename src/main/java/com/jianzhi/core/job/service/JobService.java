package com.jianzhi.core.job.service;

import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.job.model.JobDetailInfo;
import com.jianzhi.core.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by daniel on 16/1/4.
 */
public interface JobService {
    void save(Job job);
    Job findById(Long id);
    List<Job> findByUser(User user);
    void delete(Job job);
    JobDetailInfo findDetailInfo(Long id);
}
