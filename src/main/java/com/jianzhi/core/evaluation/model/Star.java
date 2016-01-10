package com.jianzhi.core.evaluation.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;

//@Entity
//@Table
public class Star {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int value;

    @Column
    private User fromUser;

    @Column
    private User toUser;
}
