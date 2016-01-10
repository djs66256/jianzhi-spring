package com.jianzhi.core.jobSeekerInfo.model;

import com.jianzhi.core.location.model.Location;
import com.jianzhi.core.user.model.User;

import javax.persistence.*;

//@Entity
//@Table
public class JobSeekerInfo {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Location homeLocation;


}
