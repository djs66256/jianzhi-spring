package com.jianzhi.core.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class WorkExperience {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String companyName;

    @Column(length = 32)
    private String position;

    @Column
    private String description;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
