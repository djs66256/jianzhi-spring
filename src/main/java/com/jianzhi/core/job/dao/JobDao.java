package com.jianzhi.core.job.dao;

import com.jianzhi.core.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 16/1/4.
 */
public interface JobDao extends JpaRepository<Job, Long> {
    Job findByIdAndActive(Long id, boolean active);
}
