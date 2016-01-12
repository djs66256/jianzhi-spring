package com.jianzhi.core.phone.service;


import com.jianzhi.core.phone.dao.PhoneValidateDao;
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
        Integer intCode = random.nextInt();
        String code = String.format("%04d", intCode);

        phoneValidateDao.save(phone, code);

        return code;
    }

    @Override
    public boolean isValidated(String code, String phone) {
        String code2 = phoneValidateDao.findIdentifyingCodeByPhoneNumber(phone);
        if (code != null && code.equals(code2)) {
            return true;
        }
        return false;
    }
}
