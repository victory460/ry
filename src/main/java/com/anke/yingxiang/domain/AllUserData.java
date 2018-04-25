package com.anke.yingxiang.domain;

import com.anke.yingxiang.domain.anke.UserModel;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/12/7.
 */
public class AllUserData {

    private UserModel User;
    private List<UserModel> Users;

    public UserModel getUserModel(){
        return User;
    }

    public void setUserModel(UserModel user){
        this.User = user;
    }

    public List<UserModel> getUsers() {
        return Users;
    }

    public void setUsers(List<UserModel> users) {
        Users = users;
    }
}