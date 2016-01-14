package com.jianzhi.core.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jianzhi.core.user.model.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class BaseResume {
    static public final int SALARY_BY_HOUR = 1;
    static public final int SALARY_BY_DAY = 2;
    static public final int SALARY_BY_MONTH = 3;
//    static public int SALARY_BY_ONCE = 4;


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
