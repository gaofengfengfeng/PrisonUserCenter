package com.gaofeng.prisonusercenter.beans.prisoner;

import com.gaofeng.prisonusercenter.beans.common.BaseReq;

/**
 * @Author: gaofeng
 * @Date: 2018-08-24
 * @Description:
 */
public class FindByNameReq extends BaseReq {
    private String prisonerName;

    public String getPrisonerName() {
        return prisonerName;
    }

    public void setPrisonerName(String prisonerName) {
        this.prisonerName = prisonerName;
    }

    @Override
    public String toString() {
        return "FindByNameReq{" +
                "prisonerName='" + prisonerName + '\'' +
                '}';
    }
}
