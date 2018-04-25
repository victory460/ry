package com.anke.yingxiang.service.userinfo;

import com.anke.yingxiang.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

    public UserInfo findByEmail(String email);

//    public Long findIdByEmail(String email);

    public UserInfo findByOpenid(String openid);
}
