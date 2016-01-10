package com.jianzhi.core.resume.dao;

import com.jianzhi.core.resume.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 16/1/3.
 */
public interface WorkExperienceDao extends JpaRepository<WorkExperience, Long> {
}
