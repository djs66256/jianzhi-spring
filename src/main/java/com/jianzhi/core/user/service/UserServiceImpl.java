package com.jianzhi.core.user.service;

import com.jianzhi.core.user.dao.UserDao;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by daniel on 15/6/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public User createUser(String username, String password) {
        User user = new User(username, password);

        save(user);
        return user;
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public User findOne(Long id) {
        return userDao.findOne(id);
    }
}
