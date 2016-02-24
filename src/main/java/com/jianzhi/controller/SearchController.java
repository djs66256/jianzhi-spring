package com.jianzhi.controller;

import com.jianzhi.core.job.service.JobService;
import com.jianzhi.core.search.model.JobFilterItem;
import com.jianzhi.core.search.service.SearchService;
import com.jianzhi.core.user.service.UserService;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/map")
    public Object mapSearch(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) double lat,
            @RequestParam(required = false) double lon,
            @RequestParam(required = false) double range
    ) {
        if (type == null) {
            return new ReturnMessage(ReturnMessage.ERROR, "参数错误");
        }
        else {
            List mapItemList = null;
            if ("all".equals(type)) {
                mapItemList = searchService.findMapItemsByLocation(lat, lon, range);
            }
            else if ("company".equals(type)) {
                mapItemList = searchService.findMapCompanyItemsByLocation(lat, lon, range);
            }
            else if ("jobseeker".equals(type)) {
                mapItemList = searchService.findMapJobseekerItemsByLocation(lat, lon, range);
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "参数错误");
            }
            return new ReturnMessage(ReturnMessage.SUCCESS, mapItemList);
        }
    }
}
