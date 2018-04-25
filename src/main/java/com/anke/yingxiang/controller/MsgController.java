package com.anke.yingxiang.controller;

import com.anke.yingxiang.domain.MyResponse;
import com.anke.yingxiang.domain.UserInfo;
import com.anke.yingxiang.domain.anke.msg.*;
import com.anke.yingxiang.rest.RestService;
import com.anke.yingxiang.service.cookie.CookieService;
import com.anke.yingxiang.service.userinfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RestService restService;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private UserInfoService userInfoService;


    private String openId() {
        return cookieService.getOpenId(httpServletRequest);
    }

    @RequestMapping("/msgByUser")
    public MyResponse messageByUser(@RequestParam(name = "page") int page) {
        boolean onlyUnRead = false;
        String openid = openId();
        //TODO 微信正式环境，不需要调用下行代码，应为userInfo初始化时会调用
        restService.weChatLoginAnke(openid, true);
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        MessageRequestModel messageRequestModel = new MessageRequestModel(10000, page, 0, onlyUnRead, userInfo.getUserid());
        MessagesModel messagesModel = restService.messagesByUser(openid, messageRequestModel);

        List<MsgInfo> msgInfos = new ArrayList<>();
        List<MessageTargetMappings> messageTargetMappings = messagesModel.getMessageTargetMappings();

        for (int i = 0; i < messageTargetMappings.size(); i++) {
            MsgInfo msgInfo = new MsgInfo();
            MessageTargetMappings one = messageTargetMappings.get(i);

            msgInfo.setId(one.getMessage().getId());
            msgInfo.setUnread(!one.isRead());
            msgInfo.setContent(one.getMessage().getContent());
            msgInfo.setFrom(one.getMessage().getSender().getName());
            msgInfo.setType(one.getMessage().getMessageType().getDisplayName());
            msgInfo.setTitle(one.getMessage().getTitle());
            String time = one.getMessage().getDateCreated();
            msgInfo.setTime(time.replace("T", " ").substring(0, time.length() - 3));

            msgInfos.add(msgInfo);
        }

        if(msgInfos.size() > 0){
            return new MyResponse(200, "成功获取消息", msgInfos);
        }

        return new MyResponse(201, "获取失败", null);
    }

    @RequestMapping("/msgDelete")
    public int messageDelete(@RequestParam(name = "messageId") String messageId) {
        String openid = openId();
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        int result = restService.messageDelete(openid, messageId, userInfo.getUserid());
        return result;
    }

    @RequestMapping("/msgRead")
    public int messageRead(@RequestParam(name = "messageId") String messageId) {
        String openid = openId();
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        MsgRequestModel msgRequestModel = new MsgRequestModel();
        msgRequestModel.setMessageId(messageId);
        msgRequestModel.setRead(true);
        msgRequestModel.setReceiverId(userInfo.getUserid());

        int result = restService.messageRead(openid, msgRequestModel);
        return result;
    }


}
