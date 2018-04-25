package com.anke.yingxiang.util;

import java.io.*;
import java.util.*;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.*;
import com.anke.yingxiang.domain.oss.OssCallback;
import com.anke.yingxiang.domain.oss.OssInfo;
import com.anke.yingxiang.domain.oss.OssToken;
import com.anke.yingxiang.rest.Api;
import com.anke.yingxiang.rest.RestService;
import com.anke.yingxiang.service.cookie.CookieService;
import com.anke.yingxiang.service.RedisService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("ossUtil")
public class OssUtil {

    @Autowired
    private RestService restService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CookieService cookieService;

    private String openid(){
        return cookieService.getOpenId(httpServletRequest);
    }

    public OssToken getReadOssToken() {

        OssToken ossToken = redisService.getReadOssToken();
        if (ossToken != null) {
            return ossToken;
        }

        OssInfo ossInfo = restService.getReadStsToken(openid());
        if (ossInfo != null) {
            redisService.setReadOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }

    public OssToken getWriteDeliverOssToken() {
        OssToken ossToken = redisService.getWriteDeliverOssToken();
        if (ossToken != null) {
            return ossToken;
        }
        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_DELIVER_RECORDS_STS_TOKEN, openid());
        if (ossInfo != null) {
            redisService.setWriteDeliverOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }

    public OssToken getWriteInstallOssToken() {
        OssToken ossToken = redisService.getWriteInstallOssToken();
        if (ossToken != null) {
            return ossToken;
        }
        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_INSTALL_RECORDS_STS_TOKEN, openid());
        if (ossInfo != null) {
            redisService.setWriteInstallOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }


    public OssToken getWriteTrainOssToken() {
        OssToken ossToken = redisService.getWriteTrainOssToken();
        if (ossToken != null) {
            return ossToken;
        }
        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_TRAIN_RECORDS_STS_TOKEN, openid());
        if (ossInfo != null) {
            redisService.setWriteTrainOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }

    public OssToken getWriteFeedbackOssToken() {
        OssToken ossToken = redisService.getWriteFeedbackOssToken();
        if (ossToken != null) {
            return ossToken;
        }
        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_FEEDBACK_RECORDS_STS_TOKEN, openid());
        if (ossInfo != null) {
            redisService.setWriteFeedbackOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }


    public OssToken getWriteFixOssToken() {
        OssToken ossToken = redisService.getWriteFixOssToken();
        if (ossToken != null) {
            return ossToken;
        }
        OssInfo ossInfo = restService.getWriteStsToken(Api.GET_WRITE_MAINTENANCE_RECORDS_STS_TOKEN, openid());
        if (ossInfo != null) {
            redisService.setWriteFixOssToken(ossInfo.getData());
            return ossInfo.getData();
        }
        return null;
    }


    public String getDeliverObjectKey(String productName, String imageName) {
        return productName + "/deliver/" + imageName;
    }

    public String getInstallObjectKey(String productName, String imageName) {
        return productName + "/installation/" + imageName;
    }

    public String getTrainObjectKey(String productName, String imageName) {
        return productName + "/training/" + imageName;
    }

    public String getFeedbackObjectKey(String productName, String feedbackId, String imageName) {
        return productName + "/feedback/" + feedbackId + "/" + imageName;
    }

    public String getFixObjectKey(String productName, String maintenanceId, String imageName) {
        return productName + "/feedback/" + maintenanceId + "/" + imageName;
    }

    public String getObjectKey(int type, String productName, String imageName, String recordId) {
        String objectKey = "";
        switch (type) {
            case ConstantUtil.UPLOAD_TYPE_DEVICE_DELIVER:
                objectKey = getDeliverObjectKey(productName, imageName);
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_INSTALL:
                objectKey = getInstallObjectKey(productName, imageName);
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_TRAIN:
                objectKey = getTrainObjectKey(productName, imageName);
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_FEEDBACK:
                objectKey = getFeedbackObjectKey(productName, recordId, imageName);
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_FIX:
                objectKey = getFixObjectKey(productName, recordId, imageName);
                break;
        }
        return objectKey;
    }

    public OssToken getOssToken(int type) {
        OssToken ossToken = null;
        switch (type) {
            case ConstantUtil.UPLOAD_TYPE_DEVICE_DELIVER:
                ossToken = getWriteDeliverOssToken();
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_INSTALL:
                ossToken = getWriteInstallOssToken();
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_TRAIN:
                ossToken = getWriteTrainOssToken();
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_FEEDBACK:
                ossToken = getWriteFeedbackOssToken();
                break;
            case ConstantUtil.UPLOAD_TYPE_DEVICE_FIX:
                ossToken = getWriteFixOssToken();
                break;
        }

        return ossToken;
    }

    private OSSClient getWriteOssClient(OssToken ossToken) {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://" + ossToken.getRegion() + ".aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = ossToken.getAccessKeyId();
        String accessKeySecret = ossToken.getAccessKeySecret();
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, ossToken.getSecurityToken());
        return ossClient;
    }

    private Callback getCallback(OssToken ossToken, String recordId) {
        OssCallback ossCallback = new Gson().fromJson(ossToken.getCallback(), OssCallback.class);
        System.out.println(ossCallback.toString());
        Callback callback = new Callback();
        callback.setCalbackBodyType(Callback.CalbackBodyType.URL);
        callback.setCallbackUrl(ossCallback.getCallbackUrl());
        callback.setCallbackHost(ossCallback.getCallbackHost());
        callback.setCallbackBody(ossCallback.getCallbackBody());
        // 获得json的key
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(ossToken.getCallbackVar());
        
        
//        jsonObject.entrySet().forEach(one -> {
//            System.out.println(one + ">>>>>>");
//            callback.addCallbackVar(one.getKey(), recordId);
//        });

        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();

        Iterator<Map.Entry<String, JsonElement>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, JsonElement> str = it.next();
            callback.addCallbackVar(str.getKey(), recordId);
            System.out.println(str);
        }

        return callback;
    }

    public String delete(List<String> obectKeys, OssToken ossToken) {
        OSSClient ossClient = getWriteOssClient(ossToken);
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(ossToken.getBucket()).withKeys(obectKeys);
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
        ResponseMessage responseMessage = deleteObjectsResult.getResponse();
        String result = "";
        if (responseMessage != null) {
            result = responseMessage.getContent().toString();
            try {
                responseMessage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ossClient.shutdown();
        }
        return result;
    }

    public String upload(String recordId, String objectKey, File file, OssToken ossToken) {
        OSSClient ossClient = getWriteOssClient(ossToken);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossToken.getBucket(), objectKey, file);
        Callback callback = getCallback(ossToken, recordId);
        putObjectRequest.setCallback(callback);

        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        ResponseMessage responseMessage = putObjectResult.getResponse();
        String result = responseMessage.getContent().toString();
        try {
            responseMessage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();
        return result;
// 读取上传回调返回的消息内容
//        byte[] buffer = new byte[1024];
//        InputStream inputStream = putObjectResult.getResponse().getContent();
//        try {
//            ByteArrayOutputStream result = new ByteArrayOutputStream();
//            int length;
//            while ((length = inputStream.read(buffer)) != -1) {
//                result.write(buffer, 0, length);
//            }
//            System.out.println(result.toString("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 一定要close，否则会造成连接资源泄漏
//            try {
//                putObjectResult.getResponse().close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            file.delete();
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//// 关闭client
//            ossClient.shutdown();
//        }

    }

}
