package com.anke.yingxiang.service.userinfo;

import com.anke.yingxiang.domain.UserInfo;
import com.anke.yingxiang.domain.anke.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserInfoService {
    public void saveUserInfo(UserModel userModel, String openid);
    public UserInfo findByOpenid(String openid);
}
