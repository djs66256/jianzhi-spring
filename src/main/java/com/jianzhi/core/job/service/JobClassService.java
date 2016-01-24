package com.jianzhi.core.job.service;

import com.jianzhi.core.job.model.JobClass;

import java.util.List;

/**
 * Created by daniel on 2016/1/21.
 */
public interface JobClassService {

    void save(JobClass jobClass);
    List<JobClass> findAll();
}
