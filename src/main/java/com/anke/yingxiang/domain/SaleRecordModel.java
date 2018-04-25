package com.anke.yingxiang.domain;

import com.anke.yingxiang.domain.anke.SaleModel;

import java.util.List;

public class SaleRecordModel {
    private SaleModel Sale;
    private List<SaleModel> Sales;

    public SaleModel getSale() {
        return Sale;
    }

    public void setSale(SaleModel sale) {
        Sale = sale;
    }

    public List<SaleModel> getSales() {
        return Sales;
    }

    public void setSales(List<SaleModel> sales) {
        Sales = sales;
    }
}
