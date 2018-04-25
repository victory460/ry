package com.anke.yingxiang.util;

/**
 * Created by qingxiangzhang on 2017/11/17.
 */
public enum MyHttpStatus {

    ERROR(1000, "ERROR"),
    AJAX_SUCCESS(200, "AJAX_SUCCESS"),
    AJAX_FAIL(201, "AJAX_FAIL");

    private int index;
    private String name;

    MyHttpStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
