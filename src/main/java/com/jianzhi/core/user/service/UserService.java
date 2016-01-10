package com.jianzhi.core.user.service;

import com.jianzhi.core.user.model.User;

/**
 * Created by daniel on 15/6/16.
 */

public interface UserService {

    User createUser(String username, String password);

    User save(User user);

    User findByName(String name);

    User findOne(Long id);
}
