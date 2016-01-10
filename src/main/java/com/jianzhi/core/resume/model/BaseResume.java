package com.jianzhi.core.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jianzhi.core.user.model.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class BaseResume {
    static public int SALARY_BY_HOUR = 1;
    static public int SALARY_BY_DAY = 2;
    static public int SALARY_BY_MONTH = 3;
//    static public int SALARY_BY_ONCE = 4;

    static public int HIGH_SCHOOL = 1;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    @Column
    private String description;

    @Column
    private int academic;

    @Column(length = 64)
    private String expectJob;

    @Column
    private int expectSalary;

    @Column int salaryType;

    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER)
    private List<WorkExperience> workExperiences = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAcademic() {
        return academic;
    }

    public void setAcademic(int academic) {
        this.academic = academic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpectJob() {
        return expectJob;
    }

    public void setExpectJob(String expectJob) {
        this.expectJob = expectJob;
    }

    public int getExpectSalary() {
        return expectSalary;
    }

    public void setExpectSalary(int expectSalary) {
        this.expectSalary = expectSalary;
    }

    public int getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(int salaryType) {
        this.salaryType = salaryType;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }
}
