package com.jianzhi.core.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jianzhi.core.user.model.User;

import javax.persistence.*;

@Entity
@Table
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    @Column(length = 32)
    private String name;

    @Column(length = 40)
    private String logo;

    @Column
    private String description;

    @Column
    private int addressCode = 0;

    @Column(length = 64)
    private String address;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(int addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
