package com.anke.yingxiang.domain;

import javax.persistence.*;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String openid;
    public String userid;
    public String roleId;
    public String name;
    public String email;
    public String position;
    public String department;
    public String telephone;
    public String picture;
    public String pictureUploadedTime;

    public UserInfo() {
    }

    public UserInfo(Long id, String userid, String roleId, String name, String email, String position, String department, String telephone, String picture, String pictureUploadedTime, String openid) {
        this.id = id;
        this.userid = userid;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.telephone = telephone;
        this.picture = picture;
        this.pictureUploadedTime = pictureUploadedTime;
        this.openid = openid;
    }

    public UserInfo(String userid, String roleId, String name, String email, String position, String department, String telephone, String picture, String pictureUploadedTime, String openid) {
        this.userid = userid;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.position = position;
        this.department = department;
        this.telephone = telephone;
        this.picture = picture;
        this.pictureUploadedTime = pictureUploadedTime;
        this.openid = openid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureUploadedTime() {
        return pictureUploadedTime;
    }

    public void setPictureUploadedTime(String pictureUploadedTime) {
        this.pictureUploadedTime = pictureUploadedTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
