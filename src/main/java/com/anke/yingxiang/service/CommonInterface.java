package com.anke.yingxiang.service;

/**
 * Created by qingxiangzhang on 2017/11/30.
 */
public interface CommonInterface<T> {
    public T call(String token) throws Exception;
}
