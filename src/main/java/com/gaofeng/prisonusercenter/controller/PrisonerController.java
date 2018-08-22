package com.gaofeng.prisonusercenter.controller;

import com.didi.meta.javalib.JLog;
import com.didi.meta.javalib.JResponse;
import com.gaofeng.prisonusercenter.beans.prisoner.RegisterReq;
import com.gaofeng.prisonusercenter.service.prisoner.PrisonerRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private PrisonerRegisterService prs;

    @Autowired
    public PrisonerController(PrisonerRegisterService prisonerRegisterService) {
        this.prs = prisonerRegisterService;
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
        Integer registerRet = prs.register(registerReq);

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
}
