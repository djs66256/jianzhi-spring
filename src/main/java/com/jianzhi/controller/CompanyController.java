package com.jianzhi.controller;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.company.service.CompanyService;
import com.jianzhi.core.user.model.User;
import com.jianzhi.util.message.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/json/user/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private boolean validateCompany(Company company) throws Exception {
        if (company.getName() == null || company.getName().isEmpty()) {
            throw new Exception("公司名称为空");
        }
        if (company.getDescription() == null || company.getDescription().isEmpty()) {
            throw new Exception("公司简介为空");
        }

        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createCompany(Company company,
                                HttpServletRequest request) {
        try {
            validateCompany(company);
            User user = (User)request.getSession().getAttribute("user");
            company.setUser(user);
            companyService.save(company);
            return new ReturnMessage(ReturnMessage.SUCCESS);
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Object editCompany(Company company,
                                HttpServletRequest request) {
        try {
            Company myCompany = companyService.findById(company.getId());
            if (myCompany != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (user.getId().equals(myCompany.getUser().getId())) {
                    validateCompany(company);
                    myCompany.setName(company.getName());
                    myCompany.setDescription(company.getDescription());

                    companyService.save(myCompany);
                    return new ReturnMessage(ReturnMessage.SUCCESS);
                }
                else {
                    return new ReturnMessage(ReturnMessage.ERROR, "不能修改其他人的公司信息");
                }
            }
            else {
                return new ReturnMessage(ReturnMessage.ERROR, "公司信息不存在");
            }
        }
        catch (Exception e) {
            return new ReturnMessage(ReturnMessage.ERROR, e.getMessage());
        }
    }
/*
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @JsonIgnoreProperties(value = {"user"})
    public Object infoCompany(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Company company = user.getCompany();
        if (company != null) {
            return new ReturnMessage(ReturnMessage.SUCCESS, company);
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "公司信息不存在");
        }
    }
*/
    @RequestMapping(value = "/upload/logo", method = RequestMethod.POST)
    public Object uploadLogoCompany(@RequestParam MultipartFile file,
                                    HttpServletRequest request) {
        // TODO:...
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }
}
