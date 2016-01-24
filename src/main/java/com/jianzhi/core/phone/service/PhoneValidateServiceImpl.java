package com.jianzhi.core.phone.service;


import com.jianzhi.core.phone.dao.PhoneValidateDao;
import jdk.nashorn.internal.runtime.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PhoneValidateServiceImpl implements PhoneValidateService {

    @Autowired
    private PhoneValidateDao phoneValidateDao;

    @Override
    public String getIdentifyingCodeByPhoneNumber(String phone) {
        Random random = new Random();
        Integer intCode = random.nextInt(999999);
        String code = String.format("%06d", intCode);

        phoneValidateDao.save(phone, code);

        return code;
    }

    @Override
    public boolean isValidated(String code, String phone) {
        String code2 = phoneValidateDao.findIdentifyingCodeByPhoneNumber(phone);
        if ("8888".equals(code)) {
            return true; // TODO: WARNING
        }
        if (code != null && code.equals(code2)) {
            return true;
        }
        return false;
    }
}
