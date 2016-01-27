package com.jianzhi.core.resume.service;


import com.jianzhi.core.resume.dao.EducationDao;
import com.jianzhi.core.resume.dao.ResumeDao;
import com.jianzhi.core.resume.dao.WorkExperienceDao;
import com.jianzhi.core.resume.model.BaseResume;
import com.jianzhi.core.resume.model.Education;
import com.jianzhi.core.resume.model.WorkExperience;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private WorkExperienceDao workExperienceDao;

    @Autowired
    private EducationDao educationDao;

    @Override
    public void save(BaseResume resume) {
        resumeDao.save(resume);
    }

    @Override
    public void saveAll(BaseResume resume) {
        if (!resume.getEducations().isEmpty()) {
            educationDao.save(resume.getEducations());
        }
        if (!resume.getWorkExperiences().isEmpty()) {
            workExperienceDao.save(resume.getWorkExperiences());
        }
        resumeDao.save(resume);
    }

    @Override
    public void saveEducation(Education education) {
        educationDao.save(education);
    }

    @Override
    public void saveWorkExperience(WorkExperience workExperience) {
        workExperienceDao.save(workExperience);
    }

    @Override
    public BaseResume findResumeByUser(User user) {
        BaseResume resume = resumeDao.findByUser(user);
        if (resume == null) {
            resume = new BaseResume();
            resume.setUser(user);
            saveAll(resume);
        }
        return resume;
    }

    @Override
    public Education findEducationById(Long id) {
        return educationDao.findOne(id);
    }

    @Override
    public WorkExperience findWorkExperienceById(Long id) {
        return workExperienceDao.findOne(id);
    }

    @Override
    public void deleteEducation(Education education) {
        educationDao.delete(education);
    }

    @Override
    public void deleteWorkExperience(WorkExperience workExperience) {
        workExperienceDao.delete(workExperience);
    }
}
