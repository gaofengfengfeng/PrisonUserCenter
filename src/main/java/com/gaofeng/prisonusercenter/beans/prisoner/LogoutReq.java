package com.gaofeng.prisonusercenter.beans.prisoner;

import com.didi.meta.javalib.JRequest;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
public class LogoutReq extends JRequest {
    private String prisonerCodeNum;

    public String getPrisonerCodeNum() {
        return prisonerCodeNum;
    }

    public void setPrisonerCodeNum(String prisonerCodeNum) {
        this.prisonerCodeNum = prisonerCodeNum;
    }

    @Override
    public String toString() {
        return "LogoutReq{" +
                "prisonerCodeNum='" + prisonerCodeNum + '\'' +
                '}';
    }
}
