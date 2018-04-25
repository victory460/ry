package com.anke.yingxiang.service.userinfo;

import com.anke.yingxiang.domain.UserInfo;
import com.anke.yingxiang.domain.anke.User;
import com.anke.yingxiang.domain.anke.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    @Override
    public void saveUserInfo(UserModel userModel, String openid) {
        UserInfo userInfo = new UserInfo(userModel.getId(), userModel.getRoleId(), userModel.getName(), userModel.getEmail(), userModel.getPosition(), userModel.getDepartment(), userModel.getTelephone(), userModel.getPicture(), userModel.getPictureUploadedTime(), openid);

        UserInfo userInfoInDB = userInfoRepository.findByEmail(userModel.getEmail());
        if (userInfoInDB != null) {
            userInfo.setId(userInfoInDB.getId());
        }
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
