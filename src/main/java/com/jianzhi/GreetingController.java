package com.jianzhi;


import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.auth.service.TokenService;
import com.jianzhi.core.phone.service.PhoneValidateService;
import com.jianzhi.core.user.dao.UserDao;
import com.jianzhi.core.user.model.User;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PhoneValidateService phoneValidateService;

    @RequestMapping("/greeting")
    public Object greeting(@CookieValue(value = "token", required = false) String token,
            @RequestParam(value="name", defaultValue="World") String name,
                             HttpServletRequest request,
                           HttpServletResponse response) {

        if (token != null) {
            Token token2 = tokenService.findByToken(token);
            if (token2 != null) {
                return new ReturnMessage(ReturnMessage.SUCCESS, token2);
            }
        }
        User user = userDao.findOne(new Long(1));
        Token token1 = tokenService.saveSession(user);

        Cookie cookie = new Cookie("token", token1.getToken());
        cookie.setMaxAge(tokenService.getMaxAge());
        response.addCookie(cookie);



        request.getSession().setAttribute("ret", new ReturnMessage(ReturnMessage.SUCCESS));
        return new ReturnMessage(ReturnMessage.SUCCESS, user);
    }

    @RequestMapping("/db")
    @Cacheable()
    public String db() {
//        User user = new User();
//        user.setName("haha");
//        userDao.save(user);

        return UUID.randomUUID().toString();

//        return "success";
    }

    @RequestMapping("/user/test")
    public Object userTest() {
        return "hah";
    }


    class Body {
        public String a;
        public String b;
    }

    @RequestMapping(value = "/json/body", method = RequestMethod.POST)
    public Object postBody(@RequestParam MultipartFile body) {

        if (body!=null) {

        }

        return null;
    }

    @RequestMapping("/json/phone")
    public Object phone(@RequestParam String phone) {
        return phoneValidateService.getIdentifyingCodeByPhoneNumber(phone);
    }

}
