package com.jianzhi.core.user.model;

import com.jianzhi.core.company.model.Company;
import com.jianzhi.core.job.model.Job;
import com.jianzhi.core.resume.model.BaseResume;

import java.util.List;

/**
 * Created by daniel on 16/1/25.
 */
public class JobSeekerUserInfo extends User {

//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private BaseResume resume;

    public JobSeekerUserInfo(User user) {
        super(user);
    }

    public BaseResume getResume() {
        return resume;
    }

    public void setResume(BaseResume resume) {
        this.resume = resume;
    }
}
