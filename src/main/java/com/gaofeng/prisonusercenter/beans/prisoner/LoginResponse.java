package com.gaofeng.prisonusercenter.beans.prisoner;

import com.didi.meta.javalib.JResponse;

/**
 * @Author: gaofeng
 * @Date: 2018-08-22
 * @Description:
 */
public class LoginResponse extends JResponse {
    private String data;

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data='" + data + '\'' +
                '}';
    }
}
