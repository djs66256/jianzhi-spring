package com.jianzhi.core.auth.service;

import com.jianzhi.core.auth.dao.TokenDao;
import com.jianzhi.core.auth.model.Token;
import com.jianzhi.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;

    @Value("${session.expires.days}")
    private String expiresDays;

    public Token findByToken(String token) {
        return tokenDao.findByToken(token);
    }

    public User findByTokenAndUserId(String token, Long userId) {
        Token token1 = findByToken(token);
        if (token1.getUser().getId() == userId) {
            return token1.getUser();
        }
        return null;
    }

    public void save(Token token) {
        tokenDao.save(token);
    }

    public Token saveSession(User user) {
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString().replace("-",""));
        token.setUser(user);
        token.setExpireDate(new Date(new Date().getTime() + new Long(expiresDays)*24*3600*1000));

        save(token);
        return token;
    }

    @Override
    public int getMaxAge() {
        return new Integer(expiresDays)*24*3600;
    }
}
