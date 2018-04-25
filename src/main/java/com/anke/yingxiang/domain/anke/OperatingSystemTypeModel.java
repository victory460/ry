package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class OperatingSystemTypeModel implements Serializable {

    private static final long serialVersionUID = -7060210544600464486L;

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}