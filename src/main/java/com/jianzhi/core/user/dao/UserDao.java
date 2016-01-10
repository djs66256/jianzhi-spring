package com.jianzhi.core.user.dao;

import com.jianzhi.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daniel on 15/6/16.
 */

public interface UserDao extends JpaRepository<User, Long> {

    User findByName(String name);

}
