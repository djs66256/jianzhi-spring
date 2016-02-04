package com.jianzhi.controller;

import com.jianzhi.core.job.service.JobService;
import com.jianzhi.core.search.model.JobFilterItem;
import com.jianzhi.core.search.service.SearchService;
import com.jianzhi.core.user.service.UserService;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/json/user/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private UserService userService;

    class JobFilter {
        final static public String TIME_ASC = "asc";
        final static public String TIME_DESC = "desc";

        private String timeOrder;
        private int jobClass;
        //private int salaryRange;
        private int district;


    }

    class PersonFilter {
        private int educationLevel;
    }

    @RequestMapping("/job/filter")
    public Object jobSearch(
//            @RequestBody JobFilter jobFilter,
            HttpServletRequest request) {
        List<JobFilterItem> items = searchService.findByFilter();

        return new ReturnMessage(ReturnMessage.SUCCESS, items);
    }

    @RequestMapping("/person/filter")
    public Object personSearch(
//            @RequestBody PersonFilter personFilter,
            HttpServletRequest request) {
        return new ReturnMessage(ReturnMessage.SUCCESS, userService.findAll());
    }
}
