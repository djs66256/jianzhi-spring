package com.jianzhi.core.phone.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class PhoneValidateDapImpl implements PhoneValidateDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.redis.database.phone}")
    private int database;

    @Value("${spring.redis.database.phone.seconds}")
    private int seconds;

    private String keyWithPhoneNumber(String phone) {
        return "phone:"+phone;
    }

    @Override
    public void save(String phoneNumber, String identifyingCode) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(database);
                byte[] key = keyWithPhoneNumber(phoneNumber).getBytes();
                connection.expire(key, seconds);
                connection.set(key, identifyingCode.getBytes());
                return null;
            }
        });
    }

    @Override
    public String findIdentifyingCodeByPhoneNumber(String phoneNumber) {
        Object object = redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.select(database);
                return connection.get(keyWithPhoneNumber(phoneNumber).getBytes());
            }
        });
        try {
            return new String((byte[])object);
        }
        catch (Exception e) {
            return null;
        }
    }

}
