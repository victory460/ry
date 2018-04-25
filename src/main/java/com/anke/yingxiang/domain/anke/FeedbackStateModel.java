package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */

public class FeedbackStateModel implements Serializable {

    private static final long serialVersionUID = -7059210541289864481L;

    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
