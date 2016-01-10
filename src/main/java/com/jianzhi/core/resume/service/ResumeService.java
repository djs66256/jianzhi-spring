package com.jianzhi.core.resume.service;

import com.jianzhi.core.resume.model.BaseResume;
import com.jianzhi.core.resume.model.Education;
import com.jianzhi.core.resume.model.WorkExperience;
import org.springframework.stereotype.Service;

public interface ResumeService {

    void save(BaseResume resume);
    Education findEducationById(Long id);
    WorkExperience findWorkExperienceById(Long id);
}
