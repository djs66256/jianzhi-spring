package com.jianzhi.controller;

import com.jianzhi.core.phone.service.PhoneValidateService;
import com.jianzhi.util.message.ReturnMessage;
import com.jianzhi.util.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json/phone")
public class PhoneController {

    @Autowired
    private PhoneValidateService phoneValidateService;


    @RequestMapping("/validate")
    public Object validatePhone(@RequestParam String phone) {
        if (Validate.isPhone(phone)) {
            String identify = phoneValidateService.getIdentifyingCodeByPhoneNumber(phone);
            return new ReturnMessage(ReturnMessage.SUCCESS, identify);
        }
        else {
            return new ReturnMessage(ReturnMessage.ERROR, "请输入正确的手机号");
        }
    }
}
