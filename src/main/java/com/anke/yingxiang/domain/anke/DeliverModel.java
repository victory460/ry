package com.anke.yingxiang.domain.anke;


import java.util.List;

public class DeliverModel {

    // 注意Id 和 DeliverRecordId有点混乱
    private String Id;
    private String DeliverRecordId;
    private String ProductId;
    private String SaleId;
    private String Description;
    private String Principal;
    private String DepartureDate;
    private String ArrivalDate;
    private List<DeliverRecordReportsModel> DeliverRecordReports; // upload set null
    //    private DeviceModel Product;
    private SaleModel Sale;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDeliverRecordId() {
        return DeliverRecordId;
    }

    public void setDeliverRecordId(String deliverRecordId) {
        DeliverRecordId = deliverRecordId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getSaleId() {
        return SaleId;
    }

    public void setSaleId(String saleId) {
        SaleId = saleId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrincipal() {
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public List<DeliverRecordReportsModel> getDeliverRecordReports() {
        return DeliverRecordReports;
    }

    public void setDeliverRecordReports(List<DeliverRecordReportsModel> deliverRecordReports) {
        DeliverRecordReports = deliverRecordReports;
    }

//    public DeviceModel getProduct() {
//        return Product;
//    }
//
//    public void setProduct(DeviceModel product) {
//        Product = product;
//    }

    public SaleModel getSale() {
        return Sale;
    }

    public void setSale(SaleModel sale) {
        Sale = sale;
    }
}