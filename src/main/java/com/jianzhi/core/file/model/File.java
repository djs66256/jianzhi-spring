package com.jianzhi.core.file.model;

import javax.persistence.*;

@Entity
@Table
public class File {
    final public static int IMAGE = 1;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 32)
    private String name;

    @Column
    private int type;

    public File(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
