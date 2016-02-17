package com.jianzhi.core.message.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class Message {
    final public static int MESSAGE = 1;
    final public static int JOB = 2;
    final public static int RESUME = 3;

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int type;

    @Column(length = 32)
    private String uuid;

//    @Column
//    private boolean read;

    @Column
    private Date time = new Date();

    @Column
    private String text;

//    @Column(length = 32)
//    private String imageId;

    @Column
    private boolean downloaded = false;


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

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDownloaded() {
        return downloaded;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }
}
