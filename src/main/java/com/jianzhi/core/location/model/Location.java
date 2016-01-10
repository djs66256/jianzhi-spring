package com.jianzhi.core.location.model;

import com.jianzhi.core.user.model.User;

import javax.persistence.*;

@Entity
@Table
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private double longitude; //经度

    @Column
    private double latitude; //维度

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
