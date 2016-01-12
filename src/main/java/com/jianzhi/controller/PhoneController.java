package com.jianzhi.controller;

import com.jianzhi.util.message.ReturnMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json/phone")
public class PhoneController {

    @RequestMapping("/validate")
    public Object phoneValidate() {
        return new ReturnMessage(ReturnMessage.SUCCESS);
    }
}
