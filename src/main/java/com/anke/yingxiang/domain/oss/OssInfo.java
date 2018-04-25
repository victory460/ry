package com.anke.yingxiang.domain.oss;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/12/6.
 */
public class OssInfo implements Serializable{

    private static final long serialVersionUID = 1643645512197915351L;

    private OssToken Data;

    public OssToken getData() {
        return Data;
    }

    public void setData(OssToken data) {
        Data = data;
    }
}
