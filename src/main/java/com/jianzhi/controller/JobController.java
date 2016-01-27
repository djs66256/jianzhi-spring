package com.jianzhi.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.job.model.JobDetailInfo;
import com.jianzhi.core.job.service.JobService;
import com.jianzhi.core.user.model.User;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/json/user/job", method = RequestMethod.POST)
public class JobController {

    @Autowired
    private JobService jobService;

    private boolean validateJob(Job job) throws Exception{
        if (job.getTitle() == null || job.getTitle().isEmpty()) {
            throw new Exception("标题不能为空");
        }

        if (job.getDetail() == null || job.getDetail().isEmpty()) {
            throw new Exception("工作内容不能为空");
        }

        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @JsonIgnoreProperties({"user","active","status"})
    public Object createJob(@RequestBody Job job,
                            HttpServletRequest request) {
        try {
            validateJob(job);
            User user = (User)request.getSession().getAttribute("user");
            job.setUser(user);
            jobService.save(job);
            return new ReturnMessage(ReturnMessage.SUCCESS, job);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }


    @RequestMapping("/delete")
    public Object deleteJob(@RequestParam(required = false) String id,
                            HttpServletRequest request) {
        try {
            Job job = jobService.findById(new Long(id));
            if (job != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (job.getUser().getId().equals(user.getId())) {
                    jobService.delete(job);
                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
                else {
                    return new ReturnMessage(ReturnMessage.ERROR, "你无权删除该工作");
                }
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "该工作不存在");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping("/edit")
    public Object editJob(@RequestBody Job job,
                          HttpServletRequest request) {
        try {
            Job myJob = jobService.findById(job.getId());
            if (myJob != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (user.getId().equals(myJob.getUser().getId())) {
                    validateJob(job);
                    myJob.setTitle(job.getTitle());
                    myJob.setDetail(job.getDetail());
                    myJob.setSalary(job.getSalary());
                    myJob.setSalaryType(job.getSalaryType());

                    jobService.save(myJob);
                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
                else {
                    return new ReturnMessage(ReturnMessage.ERROR, "不能修改其他人的工作信息");
                }
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "工作信息不存在");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping("/info")
    public Object infoJob(@RequestParam String id) {
        JobDetailInfo job = jobService.findDetailInfo(new Long(id));
        if (job != null) {
            return new ReturnMessage(ReturnMessage.SUCCESS, job);
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "工作信息不存在");
        }
    }

    @RequestMapping("/my/info")
    public Object myJobList(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        List<Job> jobList = jobService.findByUser(user);

        return new ReturnMessage(ReturnMessage.SUCCESS, jobList);
    }

}
