package com.jianzhi.core.controller;

import com.jianzhi.core.util.message.ReturnMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/json", method = RequestMethod.POST)
public class SearchController {

    @RequestMapping("/job")
    public Object jobSearch(HttpServletRequest request) {

        return new ReturnMessage(ReturnMessage.ERROR, "");
    }

    @RequestMapping("/person")
    public Object personSearch(HttpServletRequest request) {

        return new ReturnMessage(ReturnMessage.ERROR, "");
    }
}
