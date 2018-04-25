package com.anke.yingxiang.controller;

import com.anke.yingxiang.domain.WxConfigModel;
import com.anke.yingxiang.domain.WxTokenModel;
import com.anke.yingxiang.domain.anke.msg.MsgRequestModel;
import com.anke.yingxiang.domain.menu.Button;
import com.anke.yingxiang.domain.menu.SubButton;
import com.anke.yingxiang.domain.menu.WxMenu;
import com.anke.yingxiang.domain.msg.*;
import com.anke.yingxiang.domain.oss.OssToken;
import com.anke.yingxiang.rest.Api;
import com.anke.yingxiang.rest.RestService;
import com.anke.yingxiang.util.ConstantUtil;
import com.anke.yingxiang.util.OssUtil;
import com.anke.yingxiang.util.WxUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by qingxiangzhang on 2017/12/4.
 */
@Controller
@RequestMapping("wx")
public class WxPubController {

    //此处TOKEN即我们刚刚所填的token
    private String TOKEN = "anke";

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${wx.server.path}")
    private String BASE_URL;

    @Value("${wx.app.id}")
    private String WX_APP_ID;

    @Value("${wx.app.secret}")
    private String WX_APP_SECRET;

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private RestService restService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity test() {
        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * 获得签名等信息，用于前段微信config
     *
     * @param url
     * @return
     */
    @RequestMapping(value = "/wxparams", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity wxparams(@RequestParam(name = "url") String url) {
        // 从redirectUrl中移除端口号
        // TODO 测试用
//        String[] urls = url.split(":8080");
//        url = "";
//        for (int i = 0; i < urls.length; i++) {
//            url += urls[i];
//        }
        WxConfigModel wxConfigModel = wxUtil.signature(url);
        return new ResponseEntity(wxConfigModel, HttpStatus.OK);
    }

    /**
     * @param params 格式为 key1=value1&key2=value2&key3=value3
     * @return
     */
    @RequestMapping(value = "/openIdUrl", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity openIdUrl(@RequestParam(name = "params") String params) {
        // 从redirectUrl中移除端口号
        String[] urls = BASE_URL.split(":8080");
        String url = "";
        for (int i = 0; i < urls.length; i++) {
            url += urls[i];
        }
        url = url + "/wx/openId?params=" + URLEncoder.encode(params);
//        String url = BASE_URL + "/wx/openId";
//        url = URLEncoder.encode(url);
        url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WX_APP_ID + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        System.out.println(">>>>>>>>" + url);
        return new ResponseEntity(url, HttpStatus.OK);
    }

    @RequestMapping(value = "/openId", method = {RequestMethod.GET, RequestMethod.POST})
    public String openId(String code, @RequestParam("params") String params) {
        if (code.length() > 0) {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WX_APP_ID + "&secret=" + WX_APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
            String openid = restService.getOpenid(url);
            System.out.println(">>>>>>" + openid);
            return "redirect:" + BASE_URL + "/index.html?" + params + "&openId=" + openid;
        }
        return "error";
    }

    @RequestMapping(value = "/verify/{file}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getMPVerifyFile(@PathVariable String file) {
        return ResponseEntity.ok(resourceLoader.getResource("classpath:static/MP_verify_nhSVs55Arq4Obflv.txt"));
    }

//    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
//    public String test() {
//        return "redirect:http://baidu.com";
//    }

    @RequestMapping(value = "/menus", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity generateMenus() {
        WxMenu wxMenu = new WxMenu();

        Button shebei = new Button();
        shebei.setName("设备信息");
        shebei.setUrl(BASE_URL + "/index.html?name=Devices");
        shebei.setType("view");

        Button shouhou = new Button();
        shouhou.setName("售后操作");

//        http://anke.inzhi.cn:8080/index.html?name=Devices

        SubButton fahuo = new SubButton();
        fahuo.setName("发货");
        fahuo.setUrl(BASE_URL + "/index.html?name=NewDeliver&id=0");
        fahuo.setType("view");
        SubButton anzhuang = new SubButton();
        anzhuang.setName("安装");
        anzhuang.setUrl(BASE_URL + "/index.html?name=NewInstall&id=0");
        anzhuang.setType("view");
        SubButton peixun = new SubButton();
        peixun.setName("培训");
        peixun.setUrl(BASE_URL + "/index.html?name=NewTrain&id=0");
        peixun.setType("view");
        SubButton wenti = new SubButton();
        wenti.setName("问题反馈");
        wenti.setUrl(BASE_URL + "/index.html?name=NewTask");
        wenti.setType("view");
//        SubButton device = new SubButton();
//        device.setName("所有设备");
//        device.setUrl(BASE_URL+"/index.html?name=Devices");
//        device.setType("view");
        List<SubButton> shouhouSub = Arrays.asList(fahuo, anzhuang, peixun, wenti);

        shouhou.setSub_button(shouhouSub);

        Button yonghu = new Button();
        yonghu.setName("用户中心");

        SubButton xiaoxi = new SubButton();
        xiaoxi.setName("我的消息");
        xiaoxi.setUrl(BASE_URL + "/index.html?name=Msg");
        xiaoxi.setType("view");
        SubButton renwu = new SubButton();
        renwu.setName("我的任务");
        renwu.setUrl(BASE_URL + "/index.html?name=Task&id=0");
        renwu.setType("view");
        SubButton caidan = new SubButton();
        caidan.setName("我的菜单");
        caidan.setUrl(BASE_URL + "/index.html?name=MyMenu");
        caidan.setType("view");

        SubButton shezhi = new SubButton();
        shezhi.setName("账户绑定");
        shezhi.setUrl(BASE_URL + "/index.html?name=BindAnke");
        shezhi.setType("view");
        List<SubButton> yonghuSub = Arrays.asList(xiaoxi, renwu, caidan, shezhi);
        yonghu.setSub_button(yonghuSub);

        List<Button> buttons = Arrays.asList(shebei, shouhou, yonghu);

        wxMenu.setButton(buttons);

        return new ResponseEntity(wxUtil.createMenus(wxMenu), HttpStatus.OK);
    }

    /**
     * 接收并校验四个请求参数
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return echostr
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity checkName(@RequestParam(name = "signature") String signature,
                                    @RequestParam(name = "timestamp") String timestamp,
                                    @RequestParam(name = "nonce") String nonce,
                                    @RequestParam(name = "echostr") String echostr) {
        System.out.println("-----------------------开始校验------------------------");
        //排序
        String[] strArray = {TOKEN, timestamp, nonce};
        String sortString = wxUtil.sort(strArray);
        //加密
        String myString = wxUtil.sha1(sortString);
        //校验
        if (myString != null && myString != "" && myString.equals(signature)) {
            System.out.println("签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return new ResponseEntity(echostr, HttpStatus.OK);
        } else {
            System.out.println("签名校验失败");
            return new ResponseEntity("", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/sendMsg", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity sendMsg(@RequestBody MsgTemplateRequestModel msgTemplateRequestModel) {
        System.out.println(msgTemplateRequestModel.toString());
        String accessToken = wxUtil.accessToken(true);

        String time = msgTemplateRequestModel.getTime();
        String newTime = time.substring(0, 4)+"年"+time.substring(5,7)+"月"+time.substring(8,10)+"日"+" "+time.substring(11, 16);
        msgTemplateRequestModel.setTime(newTime);

        MsgTemplateResponse msg = restService.sendMsg(accessToken, msgTemplateRequestModel, BASE_URL + "/index.html?name=Msg");
        return new ResponseEntity(msg, HttpStatus.OK);
    }

}
