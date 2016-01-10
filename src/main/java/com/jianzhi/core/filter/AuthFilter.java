package com.jianzhi.core.filter;

import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.auth.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthFilter extends GenericFilterBean {

    @Autowired
    private TokenService tokenService;

    private Cookie findCookie(Cookie[] cookies, String cookieName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String path = request.getRequestURI();
            if (path.startsWith("/json/user") || path.startsWith("/user")) {
                Cookie token = findCookie(request.getCookies(), "token");
                Cookie user = findCookie(request.getCookies(), "uid");
                if (token != null && user != null) {
                    Token tokenObj = tokenService.findByToken(token.getValue());
                    if (tokenObj.getUser().getId().equals(new Long(user.getValue()))) {
                        request.getSession().setAttribute("user", tokenObj.getUser());
                    }
                    else {
                        request.getRequestDispatcher("/json/need_login").forward(request, response);
                        return;
                    }
                }
                else {
                    request.getRequestDispatcher("/json/need_login").forward(request, response);
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
