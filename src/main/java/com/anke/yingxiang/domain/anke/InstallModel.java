package com.anke.yingxiang.domain.anke;

import java.util.List;

public class InstallModel {

    private String Id;
    private String InstallationRecordId;
    private String ProductId;
    private String SaleId;
    //    private String Place;
    private String Description;
    private String Principal;
    private String InstallationDate;
    private String RegistrationCodeDeadline;
    private List<InstallRecordReportsModel> InstallationRecordReports;
//    private DeviceModel Product;

    private SaleModel Sale;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInstallationRecordId() {
        return InstallationRecordId;
    }

    public void setInstallationRecordId(String installationRecordId) {
        InstallationRecordId = installationRecordId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }
//
//    public String getPlace() {
//        return Place;
//    }
//
//    public void setPlace(String place) {
//        Place = place;
//    }

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

    public String getInstallationDate() {
        return InstallationDate;
    }

    public void setInstallationDate(String installationDate) {
        InstallationDate = installationDate;
    }

    public List<InstallRecordReportsModel> getInstallationRecordReports() {
        return InstallationRecordReports;
    }

    public void setInstallationRecordReports(List<InstallRecordReportsModel> installationRecordReports) {
        InstallationRecordReports = installationRecordReports;
    }

//    public DeviceModel getProduct() {
//        return Product;
//    }
//
//    public void setProduct(DeviceModel product) {
//        Product = product;
//    }

    public String getRegistrationCodeDeadline() {
        return RegistrationCodeDeadline;
    }

    public void setRegistrationCodeDeadline(String registrationCodeDeadline) {
        RegistrationCodeDeadline = registrationCodeDeadline;
    }

    public SaleModel getSale() {
        return Sale;
    }

    public void setSale(SaleModel sale) {
        Sale = sale;
    }
}