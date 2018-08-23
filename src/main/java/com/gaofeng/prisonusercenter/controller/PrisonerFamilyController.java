package com.gaofeng.prisonusercenter.controller;

import com.didi.meta.javalib.JLog;
import com.didi.meta.javalib.JResponse;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.RegisterReq;
import com.gaofeng.prisonusercenter.service.PrisonerFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
