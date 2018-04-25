package com.anke.yingxiang.controller;

import com.anke.yingxiang.domain.oss.OssToken;
import com.anke.yingxiang.rest.RestService;
import com.anke.yingxiang.service.cookie.CookieService;
import com.anke.yingxiang.util.OssUtil;
import com.anke.yingxiang.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by qingxiangzhang on 2017/12/6.
 */
@RestController
@RequestMapping("img")
public class ImageController {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private RestService restService;

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = ("/ossToken"), method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    public OssToken ossToken() {
        return ossUtil.getReadOssToken();
    }

    @RequestMapping(value = ("/writeToken"), method = {RequestMethod.POST, RequestMethod.GET})
    @Transactional
    public OssToken writeToken(@RequestParam(name = "type") int type) {
       return ossUtil.getOssToken(type);
    }

    /**
     * @param mediaIds
     * @param type
     * @param recordId    如果类型是问题反馈，则为：问题反馈记录id; 如果类型是维护，则为：维护记录id; 其他为：-1
     * @param productName
     * @return
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadWXFile(@RequestParam(name = "mediaIds[]") String[] mediaIds, @RequestParam(name = "type") Integer type, @RequestParam(name = "recordId") String recordId, @RequestParam(name = "productName") String productName) {

        System.out.println(mediaIds);
        System.out.println(recordId);
        System.out.println(productName);
        String accessToken = wxUtil.accessToken(true);

        for (int i = 0; i < mediaIds.length; i++) {
            String one = mediaIds[i];
            String path = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + accessToken + "&media_id=" + one;
            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                String contentType = urlConnection.getContentType();
                if (contentType != null) {
                    String[] types = contentType.split("/");
                    String imageName = UUID.randomUUID().toString().replace("-", "") + "." + types[1];

                    String objectKey = ossUtil.getObjectKey(type, productName, imageName, recordId);
                    OssToken ossToken = ossUtil.getOssToken(type);

                    File file = restService.getWXFile(path, imageName);
                    ossUtil.upload(recordId, objectKey, file, ossToken);
                    file.delete();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(path);
        }

//        Arrays.asList(mediaIds).stream().forEach(one -> {
//
//        });

        return "成功上传";
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteOssFile(@RequestParam(name = "urls[]") String[] urls, @RequestParam(name = "type") Integer type, @RequestParam(name = "recordIds[]") String[] recordIds) {

        String openid = cookieService.getOpenId(httpServletRequest);

        System.out.println(urls.toString());
        System.out.println(type);

        OssToken ossToken = ossUtil.getOssToken(type);
        String result1 = ossUtil.delete(Arrays.asList(urls), ossToken);
        String result2 = restService.deleteImgs(openid, Arrays.asList(recordIds), type);

        System.out.println(result1);
        System.out.println(result2);

        return "成功删除";
    }


}
