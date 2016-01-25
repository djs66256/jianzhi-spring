package com.jianzhi.core.job.dao;

import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by daniel on 16/1/4.
 */
public interface JobDao extends JpaRepository<Job, Long>, JobDaoCustom {
    Job findByIdAndActive(Long id, boolean active);
    List<Job> findByUser(User user);
}
