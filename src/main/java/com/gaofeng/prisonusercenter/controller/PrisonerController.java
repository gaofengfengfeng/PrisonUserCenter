package com.gaofeng.prisonusercenter.controller;

import com.didi.meta.javalib.JLog;
import com.didi.meta.javalib.JResponse;
import com.didi.meta.javalib.JToken;
import com.didi.meta.javalib.service.JRedisPoolService;
import com.gaofeng.prisonusercenter.InitConfig;
import com.gaofeng.prisonusercenter.beans.prisoner.LoginReq;
import com.gaofeng.prisonusercenter.beans.common.LoginResponse;
import com.gaofeng.prisonusercenter.beans.prisoner.LogoutReq;
import com.gaofeng.prisonusercenter.beans.prisoner.RegisterReq;
import com.gaofeng.prisonusercenter.service.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: gaofeng
 * @Date: 2018-08-21
 * @Description: 与罪犯相关的方法
 */

@RestController
@RequestMapping(value = "prisoner")
public class PrisonerController {

    private PrisonerService ps;

    @Autowired
    public PrisonerController(PrisonerService prisonerService) {
        this.ps = prisonerService;
    }

    /**
     * 罪犯用户注册
     *
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "register")
    public JResponse register(HttpServletRequest request,
                              @RequestBody @Valid RegisterReq registerReq) {
        JResponse jResponse = JResponse.initResponse(request, JResponse.class);
        JLog.info("prisoner register prisonerCodeNum=" + registerReq.getPrisonerCodeNum());
        // 对用户进行注册
        Integer registerRet = ps.register(registerReq);

        // 根据不同registerRet值，返回给前端相应的结果 0：数据库存储时出现错误 1：注册成功
        switch (registerRet) {
            case 0:
                jResponse.setErrNo(101222050);
                jResponse.setErrMsg("db error retry");
                break;
            case 1:
                break;
            case 2:
                jResponse.setErrNo(101222109);
                jResponse.setErrMsg("have registered");
                break;
            default:
                jResponse.setErrNo(101222053);
                jResponse.setErrMsg("unknown exception");
                break;
        }
        return jResponse;
    }

    /**
     * 罪犯用户登陆
     *
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public LoginResponse login(HttpServletRequest request, @RequestBody @Valid LoginReq loginReq) {
        LoginResponse loginResponse = JResponse.initResponse(request, LoginResponse.class);
        JLog.info("prisoner login prisonerCodeNum=" + loginReq.getPrisonerCodeNum());

        // 用户登陆
        Integer loginRet = ps.login(loginReq);

        // 根据不同的LoginRet值返回不同的错误信息
        switch (loginRet) {
            case 1:
                // 登陆成功，返回给前端一个token值
                // 生成token
                String token = ps.processToken(InitConfig.PRISONER_LOGIN_PRE +
                        loginReq.getPrisonerCodeNum(), InitConfig.ONE_DAY_EXPIRE);
                if (token == null) {
                    loginResponse.setErrNo(101222201);
                    loginResponse.setErrMsg("redis exception");
                    return loginResponse;
                } else {
                    // 组建返回给前端的结果
                    loginResponse.setData(token);
                }
                break;
            case 2:
                loginResponse.setErrNo(101222150);
                loginResponse.setErrMsg("unvalid prisonerCodeNum");
                break;
            case 3:
                loginResponse.setErrNo(101222151);
                loginResponse.setErrMsg("unvalid password");
                break;
            default:
                loginResponse.setErrNo(101222152);
                loginResponse.setErrMsg("unknown exception");
                break;
        }
        return loginResponse;
    }

    /**
     * 犯人注销
     *
     * @param request
     * @param logoutReq
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public JResponse logout(HttpServletRequest request, @RequestBody @Valid LogoutReq logoutReq) {
        JResponse jResponse = JResponse.initResponse(request, JResponse.class);
        JLog.info("prisoner logout prisonerCodeNum=" + logoutReq.getPrisonerCodeNum());
        // 从redis中寻找该用户的token是否还存在，如果不存在直接返回注销成功，如果存在，删除了再返回注销成功
        try {
            Jedis jedis = JRedisPoolService.getJedisPool(InitConfig.REDISPOOL).getResource();
            jedis.del(InitConfig.PRISONER_LOGIN_PRE + logoutReq.getPrisonerCodeNum());
        } catch (Exception e) {
            JLog.error("redis exception errMsg=" + e.getMessage(), 101230938);
            jResponse.setErrNo(101230938);
            jResponse.setErrMsg("redis exception");
        }
        return jResponse;
    }
}
