package com.jianzhi.core.job.model;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.user.model.User;
import sun.dc.pr.PRError;

import javax.persistence.*;

@Table
@Entity
public class Job {
    final public static int PENDING = 1;
    final public static int DEACTIVE = 2;

    final static public int SALARY_BY_NONE = 0;
    final static public int SALARY_BY_HOUR = 1;
    final static public int SALARY_BY_DAY = 2;
    final static public int SALARY_BY_MONTH = 3;
    final static public int SALARY_BY_ONCE = 4;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private boolean active;

    @Column
    private int status = PENDING;

    @Column
    private String title;

    @Column
    private String detail;

    @Column
    private int salaryType = SALARY_BY_NONE;

    @Column
    private int salary;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(int salaryType) {
        this.salaryType = salaryType;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
