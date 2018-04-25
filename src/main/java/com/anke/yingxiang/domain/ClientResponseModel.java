package com.anke.yingxiang.domain;

public class ClientResponseModel {

    private String id;

    private String name;

    private String principal;

    private String state;

    private String address;

    private String status;

    private String phone;

    private int cityId;

    private int provinceId;

    private int districtId;

    private int countryId;

    private String technician;

    public ClientResponseModel() {
    }

    public ClientResponseModel(String id, String name, String principal, String state, String address, String status, String phone, int cityId, int provinceId, int districtId, int countryId, String technician) {
        this.id = id;
        this.name = name;
        this.principal = principal;
        this.state = state;
        this.address = address;
        this.status = status;
        this.phone = phone;
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.countryId = countryId;
        this.technician = technician;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }
}
