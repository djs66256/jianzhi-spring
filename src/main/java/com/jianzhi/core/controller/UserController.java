package com.jianzhi.core.controller;


import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.auth.service.TokenService;
import com.jianzhi.core.user.model.User;
import com.jianzhi.core.user.service.UserService;
import com.jianzhi.core.util.encode.Encoder;
import com.jianzhi.core.util.message.ReturnMessage;
import com.jianzhi.core.util.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/json")
public class UserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private Encoder passwordEncoder;

    private void validateUser(String username, String password) throws Exception{
        if (username == null) {
            throw new Exception("请输入用户名");
        }
        if (password == null) {
            throw new Exception("请输入密码");
        }
        if (username.length() == 0 || username.length() > 100 || !Validate.isPhone(username)) {
            throw new Exception("用户名非法");
        }
        if (password.length() == 0) {
            throw new Exception("密码不能为空");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

                // login success
                Token token = tokenService.saveSession(user);
                Cookie cookie1 = new Cookie("token", token.getToken());
                cookie1.setMaxAge(tokenService.getMaxAge());
                Cookie cookie2 = new Cookie("uid", token.getUser().getId().toString());
                cookie2.setMaxAge(tokenService.getMaxAge());
                response.addCookie(cookie1);
                response.addCookie(cookie2);

                return new ReturnMessage(ReturnMessage.SUCCESS);
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
    @ResponseBody
    public Object register(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,
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
                validateUser(username, password);

                user = userService.createUser(username, "");
                user.setNickName(nickName);
                user.setCity(city);
                if ("m".equals(gender)) {
                    user.setGender("m");
                } else if ("f".equals(gender)) {
                    user.setGender("f");
                } else {
                    user.setGender("u");
                }
                userService.save(user);

                String encodePassword = passwordEncoder.encode(user.getId().toString(), password);
                user.setPassword(encodePassword);
                userService.save(user);

                return new ReturnMessage(ReturnMessage.SUCCESS);
            }
            catch (Exception e) {
                return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
            }
        }
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Object changePassword(@RequestParam(required = false) String oldPassword,
                                 @RequestParam(required = false) String newPassword,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        try {
            if (user.getPassword().equals(passwordEncoder.encode(user.getId().toString(), oldPassword))) {
                if (newPassword.length() < 6) {
                    return new ReturnMessage(ReturnMessage.ERROR, "密码必须是大于等于6位");
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

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public Object editUser(@RequestParam(required = false) String phone,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String nickname,
                           HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
       // TODO:,,,,
        userService.save(user);
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }

    @RequestMapping(value = "/user/upload/head", method = RequestMethod.POST)
    public Object uploadUserHead(@RequestParam MultipartFile file,
                                 HttpServletRequest request) {
        // TODO:.....
        return new ReturnMessage(ReturnMessage.ERROR, "");
    }

    @RequestMapping(value = "/user/upload/idcard", method = RequestMethod.POST)
    public Object uploadUserIdCard(@RequestParam MultipartFile file,
                                 HttpServletRequest request) {
        // TODO:.....
        return new ReturnMessage(ReturnMessage.ERROR, "");
    }

    @RequestMapping("/need_login")
    public Object needLogin() {
        return new ReturnMessage(ReturnMessage.NEED_LOGIN, "need login");
    }
}
