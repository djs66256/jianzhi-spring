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
import java.util.List;

@RestController
@RequestMapping("/json/user/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    private boolean validateResume(BaseResume resume) throws Exception {
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

        return true;
    }

    private boolean validateWork(WorkExperience workExperience) throws Exception {
        if (workExperience.getCompanyName() == null || workExperience.getCompanyName().isEmpty()) {
            throw new Exception("公司名称为空");
        }
        if (workExperience.getPosition() == null || workExperience.getPosition().isEmpty()) {
            throw new Exception("职位名称为空");
        }

        return true;
    }


//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public Object createResume(@RequestBody BaseResume resume,
//                               HttpServletRequest request) {
//        try {
//            User user = (User)request.getSession().getAttribute("user");
//            BaseResume myResume = resumeService.findResumeByUser(user);
//            validateResume(resume);
//
//            return new ReturnMessage(ReturnMessage.SUCCESS);
//        }
//        catch (Exception e) {
//            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
//        }
//    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object editResume(@RequestParam(required = false) String description,
                             @RequestParam(required = false) int educationLevel,
                             @RequestParam(required = false) String expectJob,
                             HttpServletRequest request) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            BaseResume myResume = resumeService.findResumeByUser(user);
            if (description != null) {
                myResume.setDescription(description);
            }
            if (educationLevel != 0) {
                myResume.setAcademic(educationLevel);
            }
            if (expectJob != null) {
                myResume.setExpectJob(expectJob);
            }

            resumeService.save(myResume);

            return new ReturnMessage(ReturnMessage.SUCCESS);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/work/create", method = RequestMethod.POST)
    public Object workCreateResume(@RequestBody WorkExperience workExperience,
                                   HttpServletRequest request) {
        try {
            validateWork(workExperience);

            User user = (User)request.getSession().getAttribute("user");
            BaseResume resume = resumeService.findResumeByUser(user);
            workExperience.setResume(resume);
            resumeService.saveWorkExperience(workExperience);

            return new ReturnMessage(ReturnMessage.SUCCESS, workExperience);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/work/edit", method = RequestMethod.POST)
    public Object workEditResume(@RequestBody WorkExperience workExperience,
                               HttpServletRequest request) {
        WorkExperience work = resumeService.findWorkExperienceById(workExperience.getId());
        if (work != null) {
            User user = (User)request.getSession().getAttribute("user");
            if (work.getResume().getUser().getId().equals(user.getId())) {
                try {
                    validateWork(workExperience);

                    work.setCompanyName(workExperience.getCompanyName());
                    work.setDescription(workExperience.getDescription());
                    work.setPosition(workExperience.getPosition());
                    work.setFromTime(workExperience.getFromTime());
                    work.setToTime(workExperience.getToTime());

                    resumeService.saveWorkExperience(work);

                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
                catch (Exception e) {
                    return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
                }
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "不能修改别人的简历");
            }
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "工作经历不存在");
        }
    }

    @RequestMapping(value = "/work/delete")
    public Object workCreateResume(@RequestParam List<String> workId,
                                   HttpServletRequest request) {
        try {
        // TODO: delete
            return new ReturnMessage(ReturnMessage.SUCCESS);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/education/create", method = RequestMethod.POST)
    public Object educationCreateResume(@RequestBody Education education,
                                   HttpServletRequest request) {
        try {
            validateEducation(education);

            User user = (User)request.getSession().getAttribute("user");
            BaseResume resume = resumeService.findResumeByUser(user);
            education.setResume(resume);
            resumeService.saveEducation(education);

            return new ReturnMessage(ReturnMessage.SUCCESS, education);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/education/edit", method = RequestMethod.POST)
    public Object educationEditResume(@RequestBody Education education,
                               HttpServletRequest request) {
        Education myEducation = resumeService.findEducationById(education.getId());
        if (myEducation != null) {
            User user = (User)request.getSession().getAttribute("user");
            if (myEducation.getResume().getUser().getId().equals(user.getId())) {
                try {
                    validateEducation(education);

                    myEducation.setLevel(education.getLevel());
                    myEducation.setMajor(education.getMajor());
                    myEducation.setSchool(education.getSchool());
                    myEducation.setFromTime(education.getFromTime());
                    myEducation.setToTime(education.getToTime());

                    resumeService.saveEducation(myEducation);

                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
                catch (Exception e) {
                    return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
                }
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
