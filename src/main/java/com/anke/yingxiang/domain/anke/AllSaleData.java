package com.anke.yingxiang.domain.anke;

import java.util.List;

public class AllSaleData {

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
