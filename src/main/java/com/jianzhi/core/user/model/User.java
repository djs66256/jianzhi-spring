package com.jianzhi.core.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.location.model.Location;
import com.jianzhi.core.resume.model.BaseResume;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class User {
    static public int DEFAULT = 0;
    static public int JOBSEEKER = 1;
    static public int BOSS = 2;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, unique = true)
    private String name;

    @Column(length = 50)
    @JsonIgnore
    private String password;

    @Column
    private int groupType = DEFAULT;

    @Column(length = 50)
    private String nickName;

    @Column(length = 64)
    private String headImage;

    @Column(length = 50)
    private String city;

    @Column(length = 4)
    private String gender;

    @Column
    private Date registerTime;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(User user) {
        id = user.id;
        name = user.name;
        groupType = user.groupType;
        nickName = user.nickName;
        headImage = user.headImage;
        city = user.city;
        gender = user.headImage;
        gender = user.gender;
        registerTime = user.registerTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.id.equals(((User) obj).getId());
    }
}
