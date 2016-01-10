package com.jianzhi.core.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Education {
    public static int DEFAULT = 0;
    public static int MIDDLESCHOOL = 1;
    // TODO: major

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String school;

    @Column(length = 32)
    private String major;

    @Column
    private int level = DEFAULT;

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
