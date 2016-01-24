package com.jianzhi.core.job.dao;

import com.jianzhi.core.job.model.JobDetailInfo;

/**
 * Created by daniel on 2016/1/24.
 */
public interface JobDaoCustom {

    JobDetailInfo findDetailInfoOne(Long id);
}
