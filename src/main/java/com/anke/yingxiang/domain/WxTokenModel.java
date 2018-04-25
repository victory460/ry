package com.anke.yingxiang.domain;

/**
 * Created by qingxiangzhang on 2017/12/4.
 * 微信AccessToken
 */
public class WxTokenModel {
    private String access_token;
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
