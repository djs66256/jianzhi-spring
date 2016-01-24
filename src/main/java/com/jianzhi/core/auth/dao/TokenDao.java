package com.jianzhi.core.auth.dao;

import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TokenDao extends JpaRepository<Token, Long> {
    Token findByToken(String token);
    void deleteByUser(User user);
    List<Token> findByUser(User user);
}
