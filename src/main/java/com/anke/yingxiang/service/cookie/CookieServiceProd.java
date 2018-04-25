package com.anke.yingxiang.service.cookie;

import com.anke.yingxiang.util.CookieUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component(value = "cookieService")
@Profile("prod")
public class CookieServiceProd implements CookieService {

    @Override
    public String getOpenId(HttpServletRequest httpServletRequest) {
        return CookieUtil.getCookieByName(httpServletRequest, "openid").getValue();
    }

}
