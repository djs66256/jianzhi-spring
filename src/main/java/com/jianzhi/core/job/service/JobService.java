package com.jianzhi.core.job.service;

import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.job.model.JobDetailInfo;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 16/1/4.
 */
public interface JobService {
    void save(Job job);
    Job findById(Long id);
    void delete(Job job);
    JobDetailInfo findDetailInfo(Long id);
}
