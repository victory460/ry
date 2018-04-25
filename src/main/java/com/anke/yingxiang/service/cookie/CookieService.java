package com.anke.yingxiang.service.cookie;

import com.anke.yingxiang.util.CookieUtil;
import org.apache.http.HttpRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {

    public String getOpenId(HttpServletRequest httpServletRequest);

}
