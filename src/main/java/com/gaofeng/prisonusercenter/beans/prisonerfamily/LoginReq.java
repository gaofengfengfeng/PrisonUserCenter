package com.gaofeng.prisonusercenter.beans.prisonerfamily;

import com.didi.meta.javalib.JRequest;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
public class LoginReq extends JRequest {
    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "LoginReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
