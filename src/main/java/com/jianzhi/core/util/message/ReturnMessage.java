package com.jianzhi.core.util.message;

/**
 * Created by daniel on 15/6/17.
 */
public class ReturnMessage {
    public static final int ERROR = -1;
    public static final int SUCCESS = 1;
    public static final int NEED_LOGIN = -2;

    private int retCode = SUCCESS;

    private Object content = "";

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public ReturnMessage(int retCode) {
        this(retCode, "");
    }

    public ReturnMessage(int retCode, Object content) {
        this.retCode = retCode;
        this.content = content;
    }

    public int getRetCode() {
        return retCode;
    }
}
