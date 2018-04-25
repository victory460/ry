package com.anke.yingxiang.domain;

public class RecordResponseModel {
    private String id;
    private String date;
    private String productName;
    private String productId;
    private String principal;
    private String description;
    private String location;

    public RecordResponseModel() {
    }

    public RecordResponseModel(String id, String date, String productName, String productId, String principal, String description, String location) {
        this.id = id;
        this.date = date;
        this.productName = productName;
        this.productId = productId;
        this.principal = principal;
        this.description = description;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}



