package com.anke.yingxiang.domain.anke;

import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class ClientModel implements Serializable {

    private static final long serialVersionUID = -7060210544600464487L;

    private String Id;

    private String Name;

    private District District;

    private String Address;

    private String Principal;

    private Boolean IsInUse;

    private String Technician;

    private int CityId;

    private int CountryId;

    private int ProvinceId;

    private int DistrictId;

    private String Telephone;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public Boolean getInUse() {
        return IsInUse;
    }

    public void setInUse(Boolean inUse) {
        IsInUse = inUse;
    }

    public com.anke.yingxiang.domain.anke.District getDistrict() {
        return District;
    }

    public void setDistrict(com.anke.yingxiang.domain.anke.District district) {
        District = district;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTechnician() {
        return Technician;
    }

    public void setTechnician(String technician) {
        Technician = technician;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public int getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(int districtId) {
        DistrictId = districtId;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
