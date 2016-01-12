package com.jianzhi.core.phone.dao;

/**
 * Created by daniel on 2016/1/13.
 */
public interface PhoneValidateDao {

    void save(String phoneNumber, String identifyingCode);
    String findIdentifyingCodeByPhoneNumber(String phoneNumber);

}
