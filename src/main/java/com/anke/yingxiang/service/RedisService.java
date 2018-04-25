package com.anke.yingxiang.service;

import com.anke.yingxiang.domain.oss.OssInfo;
import com.anke.yingxiang.domain.oss.OssToken;
import com.anke.yingxiang.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisService")
public class RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${oss.token.cache.time}")
    private long ossCacheTime;

    public void setAnkeToken(String openId, String token){
        redisUtil.set(openId, token, 3600l);
    }

    public String getAnkeToken(String openId){
        return (String) redisUtil.get(openId);
    }

    /**
     * 保存0.5小时
     * @param ossToken
     */
    public void setReadOssToken(OssToken ossToken) {
        redisUtil.set("readoss", ossToken, 1800l);
    }

    public OssToken getReadOssToken() {
        Object oss = redisUtil.get("readoss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

    public void setWriteDeliverOssToken(OssToken ossToken) {
        redisUtil.set("writeDeliverOss", ossToken, ossCacheTime);
    }

    public OssToken getWriteDeliverOssToken() {
        Object oss = redisUtil.get("writeDeliverOss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

    public void setWriteInstallOssToken(OssToken ossToken) {
        redisUtil.set("writeInstallOss", ossToken, ossCacheTime);
    }

    public OssToken getWriteInstallOssToken() {
        Object oss = redisUtil.get("writeInstallOss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

    public void setWriteTrainOssToken(OssToken ossToken) {
        redisUtil.set("writeTrainOss", ossToken, ossCacheTime);
    }

    public OssToken getWriteTrainOssToken() {
        Object oss = redisUtil.get("writeTrainOss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

    public void setWriteFeedbackOssToken(OssToken ossToken) {
        redisUtil.set("writeFeedbackOss", ossToken, ossCacheTime);
    }

    public OssToken getWriteFeedbackOssToken() {
        Object oss = redisUtil.get("writeFeedbackOss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

    public void setWriteFixOssToken(OssToken ossToken) {
        redisUtil.set("writeFixOss", ossToken, ossCacheTime);
    }

    public OssToken getWriteFixOssToken() {
        Object oss = redisUtil.get("writeFixOss");
        if (oss != null) {
            return (OssToken) oss;
        }
        return null;
    }

}
