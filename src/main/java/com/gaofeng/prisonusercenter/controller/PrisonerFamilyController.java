package com.gaofeng.prisonusercenter.controller;

import com.didi.meta.javalib.JLog;
import com.didi.meta.javalib.JResponse;
import com.didi.meta.javalib.service.JRedisPoolService;
import com.gaofeng.prisonusercenter.InitConfig;
import com.gaofeng.prisonusercenter.beans.common.LoginResponse;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.LoginReq;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.LogoutReq;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.RegisterReq;
import com.gaofeng.prisonusercenter.service.PrisonerFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
@RestController
@RequestMapping(value = "/prisonerFamily")
public class PrisonerFamilyController {

    private PrisonerFamilyService pfs;

    @Autowired
    public PrisonerFamilyController(PrisonerFamilyService prisonerFamilyService) {
        this.pfs = prisonerFamilyService;
    }

    /**
     * 犯属注册
     *
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "/register")
    public JResponse register(HttpServletRequest request,
                              @RequestBody @Valid RegisterReq registerReq) {
        JResponse jResponse = JResponse.initResponse(request, JResponse.class);
        JLog.info("prisonerFamily register username=" + registerReq.getUsername());

        // 注册
        Integer registerRet = pfs.register(registerReq);

        // 1:成功 2：用户名已经存在 3：身份证、手机号已经注册过 4：数据库错误
        switch (registerRet) {
            case 1:
                break;
            case 2:
                jResponse.setErrNo(101231549);
                jResponse.setErrMsg("username already exists");
                break;
            case 3:
                jResponse.setErrNo(101231550);
                jResponse.setErrMsg("have registered");
                break;
            case 4:
                jResponse.setErrNo(101231546);
                jResponse.setErrMsg("db error");
                break;
            default:
                jResponse.setErrNo(101231551);
                jResponse.setErrMsg("unknown exception");
                break;
        }
        return jResponse;
    }

    /**
     * 犯属登陆
     *
     * @param request
     * @param loginReq
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public LoginResponse login(HttpServletRequest request, @RequestBody @Valid LoginReq loginReq) {
        LoginResponse loginResponse = JResponse.initResponse(request, LoginResponse.class);
        JLog.info("prisonerFamily login username=" + loginReq.getUsername());

        // 登陆
        Integer loginRet = pfs.login(loginReq);

        // 1：成功 2：用户不存在 3：密码错误
        switch (loginRet) {
            case 1:
                // 登陆成功，返回给前端一个token值
                // 生成token
                String token = pfs.processToken(InitConfig.PRISONER_FAMILY_LOGIN_PRE +
                        loginReq.getUsername(), InitConfig.ONE_DAY_EXPIRE);
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
                loginResponse.setErrMsg("unvalid username");
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
     * 犯属注销
     *
     * @param request
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public JResponse logout(HttpServletRequest request, @RequestBody @Valid LogoutReq logoutReq) {
        JResponse jResponse = JResponse.initResponse(request, JResponse.class);
        JLog.info("prisonerFamily logout username=" + logoutReq.getUsername());
        // 从redis中寻找该用户的token是否还存在，如果不存在直接返回注销成功，如果存在，删除了再返回注销成功
        try {
            Jedis jedis = JRedisPoolService.getJedisPool(InitConfig.REDISPOOL).getResource();
            jedis.del(InitConfig.PRISONER_FAMILY_LOGIN_PRE + logoutReq.getUsername());
        } catch (Exception e) {
            JLog.error("redis exception errMsg=" + e.getMessage(), 101230938);
            jResponse.setErrNo(101230938);
            jResponse.setErrMsg("redis exception");
        }
        return jResponse;
    }
}
