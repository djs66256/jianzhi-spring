package com.jianzhi.core.auth.service;

import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.user.model.User;

public interface TokenService {

    Token findByToken(String token);

    User findByTokenAndUserId(String token, Long userId);

    void save(Token token);

    Token saveSession(User user);

    int getMaxAge();
}
