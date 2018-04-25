package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = 01234560000011111L;

    private UserModel User;

    public UserModel getUser() {
        return User;
    }

    public void setUser(UserModel user) {
        User = user;
    }
}
