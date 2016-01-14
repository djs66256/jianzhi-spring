package com.jianzhi.core.phone.service;

/**
 * Created by daniel on 2016/1/12.
 */
public interface PhoneValidateService {

    String getIdentifyingCodeByPhoneNumber(String phone);

    boolean isValidated(String code, String phone);

}
