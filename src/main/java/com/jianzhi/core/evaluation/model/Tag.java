package com.jianzhi.core.evaluation.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;

//@Entity
//@Table
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 16)
    private String content;

    @ManyToMany
    private User fromUser;

    @ManyToMany
    private User toUser;

}
