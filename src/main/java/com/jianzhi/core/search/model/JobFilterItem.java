package com.jianzhi.core.search.model;

import java.util.Date;

/**
 * Created by daniel on 2016/1/24.
 */
public class JobFilterItem {

    private Long id;
    private String title;
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
