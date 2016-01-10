package com.jianzhi.core.company.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;

@Entity
@Table
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @Column(length = 32)
    private String name;

    @Column(length = 40)
    private String logo;

    @Column
    private String description;

    @Column(length = 64)
    private String location;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
