package com.gaofeng.prisonusercenter.beans.prisonerfamily;

import com.didi.meta.javalib.JRequest;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
public class LogoutReq extends JRequest {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LogoutReq{" +
                "username='" + username + '\'' +
                '}';
    }
}
