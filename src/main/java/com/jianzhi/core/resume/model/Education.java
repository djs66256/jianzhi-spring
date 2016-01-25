package com.jianzhi.core.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Education {
    static public final int NO_LEVEL = 0;
    static public final int OTHER_SCHOOL = 10;
    static public final int JUNIOR_SCHOOL = 20; //初中
    static public final int HIGH_SCHOOL = 30; //高中
    static public final int SECONDARY_SPECIALIZED_SCHOOL = 40; //中专
    static public final int JUNIOR_COLLEGE = 50; //大专
    static public final int BACHELOR = 60; // 本科
    static public final int MASTER = 70; // 硕士
    static public final int DOCTOR = 80; // 博士

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String school;

    @Column(length = 32)
    private String major;

    @Column
    private int level = Education.NO_LEVEL;

    @Column
    private Date fromTime;

    @Column
    private Date toTime;

    @ManyToOne
    @JsonIgnore
    private BaseResume resume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public BaseResume getResume() {
        return resume;
    }

    public void setResume(BaseResume resume) {
        this.resume = resume;
    }
}
