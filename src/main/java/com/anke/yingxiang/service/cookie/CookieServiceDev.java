package com.anke.yingxiang.service.cookie;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component(value = "cookieService")
@Profile("dev")
public class CookieServiceDev implements CookieService {

    @Override
    public String getOpenId(HttpServletRequest httpServletRequest) {
//        return "oqkB0xFhrFRK0-xGIYQ4QPSabtc8"; //杨奎
        return "oqkB0xHRx3KIy0VktLYYajPhkPAY"; // 我
//        return "oqkB0xFS1tpioxq40J_EtUywRSp4"; // 商务人员
    }
}
