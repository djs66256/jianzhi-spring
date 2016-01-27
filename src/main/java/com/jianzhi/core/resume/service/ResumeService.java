package com.jianzhi.core.resume.service;

import com.jianzhi.core.resume.model.BaseResume;
import com.jianzhi.core.resume.model.Education;
import com.jianzhi.core.resume.model.WorkExperience;
import com.jianzhi.core.user.model.User;
import org.springframework.stereotype.Service;

public interface ResumeService {

    void save(BaseResume resume);
    void saveAll(BaseResume resume);
    void saveWorkExperience(WorkExperience workExperience);
    void saveEducation(Education education);
    BaseResume findResumeByUser(User user);
    Education findEducationById(Long id);
    WorkExperience findWorkExperienceById(Long id);

    void deleteWorkExperience(WorkExperience workExperience);
    void deleteEducation(Education education);
}
