package com.jianzhi.core.user.model;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.job.model.Job;

import java.util.List;

/**
 * Created by daniel on 16/1/25.
 */
public class BossUserInfo extends User {
    private Company company;

    private List<Job> jobs;

    public BossUserInfo(User user) {
        super(user);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
