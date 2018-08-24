package com.gaofeng.prisonusercenter.service;

import com.didi.meta.javalib.Conversion;
import com.didi.meta.javalib.JLog;
import com.gaofeng.prisonDBlib.model.PrisonerFamily;
import com.gaofeng.prisonDBlib.model.PrisonerFamilyMapper;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: gaofeng
 * @Date: 2018-08-23
 * @Description:
 */
@Service
public class PrisonerFamilyService extends BaseService {

    private PrisonerFamilyMapper pfm;

    @Autowired
    public PrisonerFamilyService(PrisonerFamilyMapper prisonerFamilyMapper) {
        this.pfm = prisonerFamilyMapper;
    }

    /**
     * 犯属登陆
     *
     * @param loginReq
     *
     * @return 1：成功 2：用户不存在 3：密码错误
     */
    public Integer login(LoginReq loginReq) {
        JLog.info("prisonerFamily login service username=" + loginReq.getUsername());

        // 查找数据库
        PrisonerFamily pf = pfm.findByUsername(loginReq.getUsername());
        if (pf == null) {
            JLog.warn("no user in db username=" + loginReq.getUsername());
            return 2;
        } else if (!pf.getPassword().equals(Conversion.getMD5(loginReq.getPassword() +
                pf.getSalt()))) {
            JLog.warn("password error");
            return 3;
        }
        return 1;
    }
}
