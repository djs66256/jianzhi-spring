package com.jianzhi.controller;


import com.jianzhi.core.resume.model.BaseResume;
import com.jianzhi.core.resume.model.Education;
import com.jianzhi.core.resume.model.WorkExperience;
import com.jianzhi.core.resume.service.ResumeService;
import com.jianzhi.core.user.model.User;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/json/user/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    private boolean validateResume(BaseResume resume) throws Exception {
        if (resume == null) {
            throw new Exception("简历为空");
        }

        for(Education education : resume.getEducations()) {
            validateEducation(education);
        }

        for(WorkExperience work : resume.getWorkExperiences()) {
            validateWork(work);
        }

        return true;
    }

    private boolean validateEducation(Education education) throws Exception {
        if (education.getSchool() == null || education.getSchool().isEmpty()) {
            throw new Exception("学校为空");
        }
        if (education.getLevel() == Education.DEFAULT) {
            throw new Exception("学历为空");
        }

        return true;
    }

    private boolean validateWork(WorkExperience workExperience) throws Exception {
        if (workExperience.getCompanyName() == null || workExperience.getCompanyName().isEmpty()) {
            throw new Exception("公司名称为空");
        }
        if (workExperience.getPosition() == null || workExperience.getPosition().isEmpty()) {
            throw new Exception("工作名称为空");
        }

        return true;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createResume(@RequestBody BaseResume resume,
                               HttpServletRequest request) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            validateResume(resume);
            resume.setUser(user);
            resumeService.save(resume);
            return new ReturnMessage(ReturnMessage.SUCCESS);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }
/*
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object editResume(@RequestBody BaseResume resume,
                               HttpServletRequest request) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            BaseResume myResume = user.getResume();
            if (myResume.getId() == resume.getId()) {
                if (resume.getDescription() != null && !resume.getDescription().isEmpty()) {
                    myResume.setDescription(resume.getDescription());
                }
                //TODO:....

                resumeService.save(myResume);

                return new ReturnMessage(ReturnMessage.SUCCESS);
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "只能修改自己的简历");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }
*/
    @RequestMapping(value = "/work/edit", method = RequestMethod.POST)
    public Object workEditResume(@RequestBody WorkExperience workExperience,
                               HttpServletRequest request) {
        WorkExperience work = resumeService.findWorkExperienceById(workExperience.getId());
        if (work != null) {
            User user = (User)request.getSession().getAttribute("user");
            if (work.getResume().getUser().getId() == user.getId()) {
                // TODO: ....

                return new ReturnMessage(ReturnMessage.SUCCESS);
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "不能修改别人的简历");
            }
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "工作经历不存在");
        }
    }

    @RequestMapping(value = "/education/edit", method = RequestMethod.POST)
    public Object educationEditResume(@RequestBody Education education,
                               HttpServletRequest request) {
        Education educationObj = resumeService.findEducationById(education.getId());
        if (educationObj != null) {
            User user = (User)request.getSession().getAttribute("user");
            if (educationObj.getResume().getUser().getId() == user.getId()) {
                // TODO: ....

                return new ReturnMessage(ReturnMessage.SUCCESS);
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "不能修改别人的简历");
            }
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "教育经历不存在");
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Object postResume(@RequestBody BaseResume resume,
                               HttpServletRequest request) {

        return new ReturnMessage(ReturnMessage.ERROR, "");
    }

}
