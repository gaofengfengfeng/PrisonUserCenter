package com.gaofeng.prisonusercenter.beans.prisoner;

import com.gaofeng.prisonusercenter.beans.common.BaseReq;

/**
 * @Author: gaofeng
 * @Date: 2018-08-22
 * @Description:
 */
public class LoginReq extends BaseReq {

    private String prisonerCodeNum;
    private String password;

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

    @Override
    public String toString() {
        return "LoginReq{" +
                "prisonerCodeNum='" + prisonerCodeNum + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
