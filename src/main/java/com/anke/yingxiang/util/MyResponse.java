package com.anke.yingxiang.util;

/**
 * Created by qingxiangzhang on 2017/11/17.
 */
public class MyResponse {
    public int code;
    public String desc;
    public Object content;

    public MyResponse(int code, String desc, Object content) {
        this.code = code;
        this.desc = desc;
        this.content = content;
    }
}
