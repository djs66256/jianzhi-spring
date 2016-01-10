package com.jianzhi.core.message.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;
import java.util.Date;

//@Table
//@Entity
public class Message {
    final public static int MESSAGE = 1;
    final public static int JOB = 2;
    final public static int RESUME = 3;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int type;

    @Column
    private boolean read;

    @Column
    private Date time;

    @Column
    private String content;

    @Column(length = 32)
    private String imageId;



    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;


    public Message(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

}
