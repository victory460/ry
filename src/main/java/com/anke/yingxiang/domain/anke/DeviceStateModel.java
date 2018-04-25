package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class DeviceStateModel implements Serializable {

    private static final long serialVersionUID = -7060210544600464483L;

    private String Name;
    private String DisplayName;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

}