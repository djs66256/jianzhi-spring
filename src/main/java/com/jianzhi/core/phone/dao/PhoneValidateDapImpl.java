package com.jianzhi.core.phone.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PhoneValidateDapImpl implements PhoneValidateDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void save(String phoneNumber, String identifyingCode) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(phoneNumber.getBytes(), identifyingCode.getBytes());
                return null;
            }
        });
    }

    @Override
    public String findIdentifyingCodeByPhoneNumber(String phoneNumber) {
//        Object object = redisTemplate.execute(new RedisCallback() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                return connection.get(phoneNumber.getBytes());
//            }
//        });
//        try {
//            return (String)object;
//        }
//        catch (Exception e) {
            return null;
//        }
    }

}
