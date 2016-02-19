package com.jianzhi.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.auth.service.TokenService;
import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.company.service.CompanyService;
import com.jianzhi.core.file.service.FileService;
import com.jianzhi.core.job.service.JobService;
import com.jianzhi.core.phone.service.PhoneValidateService;
import com.jianzhi.core.resume.model.BaseResume;
import com.jianzhi.core.resume.service.ResumeService;
import com.jianzhi.core.user.model.BossUserInfo;
import com.jianzhi.core.user.model.JobSeekerUserInfo;
import com.jianzhi.core.user.model.User;
import com.jianzhi.core.user.service.UserService;
import com.jianzhi.util.encode.Encoder;
import com.jianzhi.util.message.ReturnMessage;
import com.jianzhi.util.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/json")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Encoder passwordEncoder;

    @Autowired
    private FileService fileService;

    @Autowired
    private PhoneValidateService phoneValidateService;

    private void validateUser(String username, String password) throws Exception{
        if (username == null || username.isEmpty()) {
            throw new Exception("请输入用户名");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("请输入密码");
        }
        if (username.length() > 100 || !Validate.isPhone(username)) {
            throw new Exception("用户名非法");
        }
    }

    @RequestMapping(value = "/login")//, method = RequestMethod.POST)
    public Object loginRequest(@RequestParam(required = false) String username,
                               @RequestParam(required = false) String password,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            validateUser(username, password);

            User user = userService.findByName(username);
            if (user == null) {
                return new ReturnMessage(ReturnMessage.ERROR, "用户不存在");
            }
            String encodePassword = passwordEncoder.encode(user.getId().toString(), password);
            if (user.getPassword().equals(encodePassword)) {

                if (request.getCookies() != null) {
                    for (Cookie cookie : request.getCookies()) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }

                // login success
                Token token = tokenService.saveSession(user);
                Cookie cookie1 = new Cookie("token", token.getToken());
                cookie1.setMaxAge(tokenService.getMaxAge());
                cookie1.setPath("/");
                Cookie cookie2 = new Cookie("uid", token.getUser().getId().toString());
                cookie2.setMaxAge(tokenService.getMaxAge());
                cookie2.setPath("/");
                response.addCookie(cookie1);
                response.addCookie(cookie2);

                return new ReturnMessage(ReturnMessage.SUCCESS, user);
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "用户名密码错误");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout(HttpServletRequest request,
                         HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie1 = new Cookie("user", null);
        cookie1.setMaxAge(0);
        Cookie cookie2 = new Cookie("token", null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return new ReturnMessage(ReturnMessage.SUCCESS);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @JsonIgnoreProperties({"city","gender","company","jobs","location","nickName"})
    public Object register(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String validate,
                           @RequestParam(required = false) String group,
                           @RequestParam(required = false) String nickName,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String gender,
                           HttpServletRequest request) {
        User user = userService.findByName(username);

        if (user != null) {
            return new ReturnMessage(ReturnMessage.ERROR, "账号已存在");
        }
        else {
            try {
                if (validate != null && !validate.isEmpty()) {
                    if (!phoneValidateService.isValidated(validate, username)) {
                        return new ReturnMessage(ReturnMessage.ERROR, "验证码错误");
                    }
                }
                else {
                    return new ReturnMessage(ReturnMessage.ERROR, "请输入验证码");
                }

                validateUser(username, password);

                int groupType = User.BOSS;
                if ("boss".equals(group)) {
                    groupType = User.BOSS;
                }
                else if ("jobseeker".equals(group)) {
                    groupType = User.JOBSEEKER;
                }
                else {
                    return new ReturnMessage(ReturnMessage.ERROR, "清选择用户类型");
                }

                user = userService.createUser(username, "");

                user.setGroupType(groupType);

                user.setNickName(nickName);
                user.setCity(city);
                if ("m".equals(gender) || "f".equals(gender)) {
                    user.setGender(gender);
                }
                else {
                    user.setGender("u");
                }

                String encodePassword = passwordEncoder.encode(user.getId().toString(), password);
                user.setPassword(encodePassword);
                userService.save(user);

                return new ReturnMessage(ReturnMessage.SUCCESS, user);
            }
            catch (Exception e) {
                return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
            }
        }
    }

    @RequestMapping("/forgetPassword")
    public Object forgetPassword(@RequestParam(required = false) String validate,
                                 @RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password) {
        if (validate == null || validate.isEmpty()) {
            return new ReturnMessage(ReturnMessage.ERROR, "请输入验证码");
        }
        if (username == null || username.isEmpty()) {
            return new ReturnMessage(ReturnMessage.ERROR, "请输入账号");
        }
        if (password == null || password.isEmpty()) {
            return new ReturnMessage(ReturnMessage.ERROR, "请输入新密码");
        }

        if (phoneValidateService.isValidated(validate, username)) {
            User user = userService.findByName(username);
            if (user == null) {
                return new ReturnMessage(ReturnMessage.ERROR, "该账号还未注册");
            }
            try {
                user.setPassword(passwordEncoder.encode(user.getId().toString(), password));
                userService.save(user);
                tokenService.deleteSession(user);

                return new ReturnMessage(ReturnMessage.SUCCESS);
            }
            catch (Exception e) {
                return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
            }
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "验证码错误");
        }
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    public Object changePassword(@RequestParam(required = false) String oldPassword,
                                 @RequestParam(required = false) String newPassword,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        try {
            if (user.getPassword().equals(passwordEncoder.encode(user.getId().toString(), oldPassword))) {
                if (newPassword.length() < 6) {
                    return new ReturnMessage(ReturnMessage.ERROR, "密码必须大于等于6位");
                }
                else {
                    user.setPassword(passwordEncoder.encode(user.getId().toString(), newPassword));
                    userService.save(user);

                    logout(request, response);

                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "密码错误");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping("/user/my/edit/nickname")
    public Object editMyNickName(
            @RequestParam(required = false) String nickname,
            HttpServletRequest request) {
        if (nickname != null && nickname.isEmpty()) {
            return new ReturnMessage(ReturnMessage.ERROR, "姓名不能为空");
        }

        User user = (User) request.getSession().getAttribute("user");
        user.setNickName(nickname);
        userService.save(user);
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }

    @RequestMapping("/user/my/edit/gender")
    public Object editMyGender(
            @RequestParam(required = false) String gender,
            HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if ("m".equals(gender) || "f".equals(gender) || "u".equals(gender)) {
            user.setGender(gender);
            userService.save(user);
            return new ReturnMessage(ReturnMessage.SUCCESS);
        } else {
            return new ReturnMessage(ReturnMessage.ERROR, "性别非法");
        }
    }

    @RequestMapping("/user/my/edit/description")
    public Object editMyDescription(
            @RequestParam(required = false) String description,
            HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        user.setDescription(description);
        userService.save(user);
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }

    @RequestMapping(value = "/user/my/edit/info", method = RequestMethod.POST)
    public Object editUser(@RequestParam(required = false) String nickName,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String gender,
                           @RequestParam(required = false) String description,
                           HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (nickName != null && !nickName.isEmpty()) {
            user.setNickName(nickName);
        }
        if (city != null && !city.isEmpty()) {
            user.setCity(city);
        }
        if (gender != null) {
            if ("m".equals(gender) || "f".equals(gender)) {
                user.setGender(gender);
            } else {
                user.setGender("u");
            }
        }


        userService.save(user);
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }


    @RequestMapping(value = "/user/upload/head", method = RequestMethod.POST)
    public Object uploadUserHead(@RequestParam MultipartFile file,
                                 HttpServletRequest request) {
        try {
            User user = (User)request.getSession().getAttribute("user");
            String fileName = fileService.saveHeadImage(user, file);
            user.setHeadImage(fileName);
            userService.save(user);
            return new ReturnMessage(ReturnMessage.SUCCESS, fileName);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/user/upload/idcard", method = RequestMethod.POST)
    public Object uploadUserIdCard(@RequestParam MultipartFile file,
                                 HttpServletRequest request) {
        // TODO:.....
        return new ReturnMessage(ReturnMessage.ERROR, "");
    }

    @RequestMapping("/user/my/info")
    public Object getMyInfo(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");


        return new ReturnMessage(ReturnMessage.SUCCESS, user);
    }

    @RequestMapping("/user/my/allinfo")
    public Object getMyAllInfo(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user.getGroupType() == User.JOBSEEKER) {
            JobSeekerUserInfo jobSeekerUserInfo = new JobSeekerUserInfo(user);
            BaseResume resume = resumeService.findResumeByUser(user);
            jobSeekerUserInfo.setResume(resume);
            user = jobSeekerUserInfo;
        }
        else if (user.getGroupType() == User.BOSS) {
            Company company = companyService.findByUser(user);
            List jobs = jobService.findByUser(user);
            BossUserInfo bossUserInfo = new BossUserInfo(user);
            bossUserInfo.setCompany(company);
            bossUserInfo.setJobs(jobs);
            user = bossUserInfo;
        }

        return new ReturnMessage(ReturnMessage.SUCCESS, user);
    }

    @RequestMapping("/user/info")
    public Object getUserInfo(@RequestParam(required = false) String uid) {
        User user = userService.findOne(new Long(uid));
        if (user != null) {
            return new ReturnMessage(ReturnMessage.SUCCESS, user);
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "用户不存在");
        }
    }

    @RequestMapping("/need_login")
    public Object needLogin() {
        return new ReturnMessage(ReturnMessage.NEED_LOGIN, "need login");
    }
}
