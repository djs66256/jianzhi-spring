package com.jianzhi.core.auth.dao;

import com.jianzhi.core.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TokenDao extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
