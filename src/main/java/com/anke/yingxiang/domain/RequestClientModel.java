package com.anke.yingxiang.domain;

public class RequestClientModel {
    private String Id;
    private String Address;
    private int CityId;
    private int ProvinceId;
    private int CountryId;
    private int DistrictId;
    private boolean IsInUse;
    private String Name;
    private String Principal = "";
    private String Telephone;
    private String Technician;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(int districtId) {
        DistrictId = districtId;
    }

    public boolean isInUse() {
        return IsInUse;
    }

    public void setInUse(boolean inUse) {
        IsInUse = inUse;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrincipal() {
        return Principal;
    }

    public void setPrincipal(String principal) {
        Principal = principal;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getTechnician() {
        return Technician;
    }

    public void setTechnician(String technician) {
        Technician = technician;
    }
}
