package com.anke.yingxiang.domain.anke;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by qingxiangzhang on 2017/11/28.
 */
public class UserModel implements Serializable {

    private static final long serialVersionUID = 0123456L;

    private String Id;
    private String RoleId;
    private String Name;
    private String Email;
    private String Position;
    private String Department;
    private String Telephone;
    private String Picture;
    private String PictureUploadedTime;

    public String getId(){
        return Id;
    }

    public void setId(String Id){
        this.Id = Id;
    }

    public String getRoleId(){
        return RoleId;
    }

    public void setRoleId(String RoleId){
        this.RoleId = RoleId;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getEmail(){
        return Email;
    }

    public void setEmail(String email){
        this.Email = email;
    }

    public String getPosition(){
        return Position;
    }

    public void setPosition(String position){
        this.Position= position;
    }

    public String getDepartment(){
        return Department;
    }

    public void setDepartment(String department){
        this.Department = department;
    }

    public String getTelephone(){
        return Telephone;
    }

    public void setTelephone(String telephone){
        this.Telephone = telephone;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getPictureUploadedTime() {
        return PictureUploadedTime;
    }

    public void setPictureUploadedTime(String pictureUploadedTime) {
        PictureUploadedTime = pictureUploadedTime;
    }
}