package com.jianzhi.core.resume.dao;

import com.jianzhi.core.resume.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 16/1/3.
 */
public interface EducationDao extends JpaRepository<Education, Long> {
}
