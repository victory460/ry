package com.anke.yingxiang.domain;

public class SaleResponseModel {

    private String name;
    private String type;
    private String serialId;
    private String deviceId;
    private String deadlineDate;
    private String client;

    public SaleResponseModel() {
    }

    public SaleResponseModel(String name, String type, String serialId, String deviceId, String deadlineDate, String client) {
        this.name = name;
        this.type = type;
        this.serialId = serialId;
        this.deviceId = deviceId;
        this.deadlineDate = deadlineDate;
        this.client = client;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
