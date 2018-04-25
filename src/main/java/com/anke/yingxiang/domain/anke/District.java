package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

public class District implements Serializable {
    private static final long serialVersionUID = -706021054460087766L;

    private String MergeName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMergeName() {
        return MergeName;
    }

    public void setMergeName(String mergeName) {
        MergeName = mergeName;
    }
}
