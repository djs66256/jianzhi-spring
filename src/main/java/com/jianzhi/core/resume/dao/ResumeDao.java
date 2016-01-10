package com.jianzhi.core.resume.dao;

import com.jianzhi.core.resume.model.BaseResume;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResumeDao extends JpaRepository<BaseResume, Long> {
}
