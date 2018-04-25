package com.anke.yingxiang.util;

import com.anke.yingxiang.domain.WxConfigModel;
import com.anke.yingxiang.domain.WxTicketModel;
import com.anke.yingxiang.domain.WxTokenModel;
import com.anke.yingxiang.domain.menu.WxMenu;
import com.anke.yingxiang.rest.RestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by qingxiangzhang on 2017/12/4.
 */
@Component
public class WxUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RestService restService;

    @Value("${wx.app.id}")
    private String WX_APP_ID;

    @Value("${wx.app.secret}")
    private String WX_APP_SECRET;


    private static long timestamp() {
        return new Date().getTime();
    }

    private static String noncestr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取微信token
     * 先从Redis中拿去数据
     * @return
     */
    public String accessToken(Boolean firstTime) {
        String wxtoken = (String) redisUtil.get("wxtoken");
        String appId = appId();
        String appSecret = appSecret();
        if (wxtoken == null) {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
            try {
                WxTokenModel result = restService.getRestTemplate().getForObject(url, WxTokenModel.class);
                System.out.println("access_token=" + result.getAccess_token());
                redisUtil.set("wxtoken", result.getAccess_token(), (long) result.getExpires_in() * 4 / 5);
                return result.getAccess_token();
            } catch (Exception e) {
                if(firstTime){
                    accessToken(false);
                }
            }
        } else {
            return wxtoken;
        }

        return null;
    }

    /**
     * 获取微信Ticket
     *
     * @param accessToken
     * @return
     */
    public String ticket(String accessToken) {
        String wxticket = (String) redisUtil.get("wxticket");
        if (wxticket == null) {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
            try {
                WxTicketModel result = restService.getRestTemplate().getForObject(url, WxTicketModel.class);
                System.out.println("ticket=" + result.getTicket());
                redisUtil.set("wxticket", result.getTicket(), (long) result.getExpires_in() * 4 / 5);
                return result.getTicket();
            } catch (Exception e) {

            }
        } else {
            return wxticket;
        }

        return null;
    }

    public String createMenus(WxMenu wxMenu) {
        String token = accessToken(true);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        System.out.println(url);
        String json = new Gson().toJson(wxMenu);
        System.out.println(json);
        try {
            HttpEntity httpEntity = new HttpEntity(wxMenu);
            ResponseEntity<String> result = restService.getRestTemplate().exchange(url, HttpMethod.POST, httpEntity, String.class);
            return result.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public WxConfigModel signature(String url) {

        String accessToken = accessToken(true);
        String ticket = ticket(accessToken);
        String timestamp = timestamp()/1000+"";
        String noncestr = noncestr();

        System.out.println("url="+url);
        System.out.println("toekn=" + accessToken);
        System.out.println("ticket=" + ticket);
        System.out.println("timestamp=" + timestamp);
        System.out.println("noncestr=" + noncestr);

        String[] strArray = {noncestr, ticket, timestamp, url};
        //排序
//        String sortString = sort(strArray);

        String sortString = "jsapi_ticket=" + ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;

        System.out.println("sortString=" + sortString);

        //加密
        String signature = sha1(sortString);

        System.out.println("signature=" + signature);

        WxConfigModel wxConfigModel = new WxConfigModel();
        wxConfigModel.setAppId(appId());
        wxConfigModel.setTimestamp(timestamp);
        wxConfigModel.setNonceStr(noncestr);
        wxConfigModel.setSignature(signature);

        return wxConfigModel;
    }


    /**
     * 排序方法
     */
    public String sort(String[] strArray) {
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * 将字符串进行sha1加密
     *
     * @param str 需要加密的字符串
     * @return 加密后的内容
     */
    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String appId() {
        return WX_APP_ID.trim();
    }

    public String appSecret() {
        return WX_APP_SECRET.trim();
    }


}
