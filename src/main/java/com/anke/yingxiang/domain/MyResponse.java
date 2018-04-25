package com.anke.yingxiang.domain;

public class MyResponse {
    private int code;
    private String message;
    private Object data;

    public MyResponse() {
    }

    public MyResponse(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.data = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object object) {
        this.data = object;
    }
}
