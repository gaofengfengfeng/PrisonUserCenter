package com.gaofeng.prisonusercenter.beans.prisonerfamily;

import com.gaofeng.prisonusercenter.beans.common.BaseReq;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
public class RegisterReq extends BaseReq {

    private String username;
    private String password;
    private String prisonerFamilyName;
    private String idCardNo;
    private Integer gender; // 性别 0：未使用 1：男 2：女
    private String phone;
    private String homeAddress;
    private String educationDegree; // 教育程度

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrisonerFamilyName() {
        return prisonerFamilyName;
    }

    public void setPrisonerFamilyName(String prisonerFamilyName) {
        this.prisonerFamilyName = prisonerFamilyName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", prisonerFamilyName='" + prisonerFamilyName + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", educationDegree='" + educationDegree + '\'' +
                '}';
    }
}
