package com.gaofeng.prisonusercenter.service;

import com.didi.meta.javalib.Conversion;
import com.didi.meta.javalib.IdUtil;
import com.didi.meta.javalib.JLog;
import com.gaofeng.prisonDBlib.model.PrisonerFamily;
import com.gaofeng.prisonDBlib.model.PrisonerFamilyMapper;
import com.gaofeng.prisonDBlib.model.Role;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.LoginReq;
import com.gaofeng.prisonusercenter.beans.prisonerfamily.RegisterReq;
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
     * 犯属注册
     *
     * @param registerReq
     *
     * @return 1：成功 2：用户名已被使用 3：已经注册过 4：数据库错误
     */
    public Integer register(RegisterReq registerReq) {
        JLog.info("prisonerFamily register service username=" + registerReq.getUsername());

        // 查看该用户名是否已经被使用
        PrisonerFamily pfInDB = pfm.findByUsername(registerReq.getUsername());
        if (pfInDB != null) {
            JLog.warn("username already exists usernmae=" + registerReq.getUsername());
            return 2;
        }

        // 查看该身份证号或者手机号是否被注册过
        PrisonerFamily pfInDB2 = pfm.findByIdCardNoOrPhone(registerReq.getIdCardNo(),
                registerReq.getPhone());
        if (pfInDB2 != null) {
            JLog.warn("prisonerFamily have registered idCardNo=" + registerReq.getIdCardNo() + " " +
                    "phone=" + registerReq.getPhone());
            return 3;
        }

        // 构造犯属对象，准备入库
        PrisonerFamily pf = new PrisonerFamily();
        pf.setPrisonerFamilyId(IdUtil.generateLongId());
        pf.setUsername(registerReq.getUsername());
        String salt = IdUtil.createSalt();
        pf.setPassword(Conversion.getMD5(registerReq.getPassword() + salt));
        pf.setSalt(salt);
        pf.setPrisonerFamilyName(registerReq.getPrisonerFamilyName());
        pf.setIdCardNo(registerReq.getIdCardNo());
        pf.setGender(registerReq.getGender());
        pf.setPhone(registerReq.getPhone());
        pf.setHomeAddress(registerReq.getHomeAddress());
        pf.setEducationDegree(registerReq.getEducationDegree());
        pf.setRoleId(Role.Type.PRISONER_FAMILY);

        // 入库
        Integer saveRet = pfm.save(pf);
        if (!saveRet.equals(1)) {
            JLog.error("save prisonerFamily failed username=" + registerReq.getUsername(),
                    101231546);
            return 4;
        }
        return 1;
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
