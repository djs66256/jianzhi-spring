package com.jianzhi.core.job.model;

import javax.persistence.*;

@Entity
@Table
public class JobClass {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String description;


}
