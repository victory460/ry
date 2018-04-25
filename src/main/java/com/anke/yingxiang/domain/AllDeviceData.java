package com.anke.yingxiang.domain;

import com.anke.yingxiang.domain.anke.DeviceModel;

import java.util.List;

/**
 * Created by qingxiangzhang on 2017/12/7.
 */
public class AllDeviceData {

    private List<DeviceModel> Products;

    public List<DeviceModel> getProducts() {
        return Products;
    }

    public void setProducts(List<DeviceModel> products) {
        Products = products;
    }
}