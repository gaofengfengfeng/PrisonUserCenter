package com.gaofeng.prisonusercenter.beans.prisoner;

import com.gaofeng.prisonusercenter.beans.common.BaseReq;

/**
 * @Author: gaofeng
 * @Date: 2018-08-21
 * @Description:
 */
public class RegisterReq extends BaseReq {
    private String prisonerCodeNum; // 狱政编号
    private String password;
    private String prisonAddress; // 监狱地址
    private String prisonArea; // 监区
    private Integer criminalType; // 犯罪类型
    private String prisonTerm; // 刑期
    private String prisonerName;
    private String idCardNo;
    private Integer gender; // 性别 0：未使用 1：男 2：女
    private Integer age;
    private String birthDate;
    private String homeAddress;
    private Long beginTime; // 入狱时间

    public String getPrisonerCodeNum() {
        return prisonerCodeNum;
    }

    public void setPrisonerCodeNum(String prisonerCodeNum) {
        this.prisonerCodeNum = prisonerCodeNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrisonAddress() {
        return prisonAddress;
    }

    public void setPrisonAddress(String prisonAddress) {
        this.prisonAddress = prisonAddress;
    }

    public String getPrisonArea() {
        return prisonArea;
    }

    public void setPrisonArea(String prisonArea) {
        this.prisonArea = prisonArea;
    }

    public Integer getCriminalType() {
        return criminalType;
    }

    public void setCriminalType(Integer criminalType) {
        this.criminalType = criminalType;
    }

    public String getPrisonTerm() {
        return prisonTerm;
    }

    public void setPrisonTerm(String prisonTerm) {
        this.prisonTerm = prisonTerm;
    }

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "prisonerCodeNum='" + prisonerCodeNum + '\'' +
                ", password='" + password + '\'' +
                ", prisonAddress='" + prisonAddress + '\'' +
                ", prisonArea='" + prisonArea + '\'' +
                ", criminalType=" + criminalType +
                ", prisonTerm='" + prisonTerm + '\'' +
                ", prisonerName='" + prisonerName + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", birthDate='" + birthDate + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", beginTime=" + beginTime +
                '}';
    }
}
