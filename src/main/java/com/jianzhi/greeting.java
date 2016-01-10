package com.jianzhi;

/**
 * Created by daniel on 15/12/21.
 */
public class greeting {
    private final long id;
    private final String content;

    public greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
