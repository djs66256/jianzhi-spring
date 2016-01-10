package com.jianzhi.core.auth.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Token {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String token;

    @Column
    private Date expireDate;

    @ManyToOne
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
