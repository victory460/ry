package com.anke.yingxiang.domain.oss;

import java.io.Serializable;

public class OssCallback implements Serializable{

    private static final long serialVersionUID = 1643645512197915353L;

    private String callbackUrl;
    private String callbackHost;
    private String callbackBody;
    private String callbackBodyType;

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getCallbackHost() {
        return callbackHost;
    }

    public void setCallbackHost(String callbackHost) {
        this.callbackHost = callbackHost;
    }

    public String getCallbackBody() {
        return callbackBody;
    }

    public void setCallbackBody(String callbackBody) {
        this.callbackBody = callbackBody;
    }

    public String getCallbackBodyType() {
        return callbackBodyType;
    }

    public void setCallbackBodyType(String callbackBodyType) {
        this.callbackBodyType = callbackBodyType;
    }

    @Override
    public String toString() {
        return "OssCallback{" +
                "callbackUrl='" + callbackUrl + '\'' +
                ", callbackHost='" + callbackHost + '\'' +
                ", callbackBody='" + callbackBody + '\'' +
                ", callbackBodyType='" + callbackBodyType + '\'' +
                '}';
    }
}
