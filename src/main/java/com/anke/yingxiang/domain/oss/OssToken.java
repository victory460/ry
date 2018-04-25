package com.anke.yingxiang.domain.oss;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/12/6.
 */
public class OssToken implements Serializable{

    private static final long serialVersionUID = 1643645512197915352L;

    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String region;
    private String bucket;
    private String expire;
    private String callback;
    private String callbackVar;
    private String message;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getCallbackVar() {
        return callbackVar;
    }

    public void setCallbackVar(String callbackVar) {
        this.callbackVar = callbackVar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
